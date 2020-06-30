package service.AAADEVCRUD.Actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import service.AAADEVCRUD.Entity.Facturacion;
import service.AAADEVCRUD.Service.FacturacionImpl;
import service.AAADEVCRUD.util.PartToString;

//import com.avaya.collaboration.ssl.util.SSLProtocolType;
//import com.avaya.collaboration.ssl.util.SSLUtilityException;
//import com.avaya.collaboration.ssl.util.SSLUtilityFactory;

/**
 *
 * @author umansilla
 */
public class NotificacionVencimientoDePagoCloudAction {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public NotificacionVencimientoDePagoCloudAction(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    
    public void changeStatus() throws IOException, ServletException, Exception{
        Facturacion facturacion = new Facturacion(Integer.parseInt(new PartToString(request).getStringValue("numeroDeCuenta")));
        Facturacion itemFacturación = new FacturacionImpl().obtenerFacturacion(facturacion);
        if(itemFacturación != null){
            if(itemFacturación.getStatus() == 0){
                itemFacturación.setStatus(1);
            }else{
                itemFacturación.setStatus(0);
            }
            new FacturacionImpl().editarFacturacion(itemFacturación);
            response.getWriter().println(new JSONObject().put("status", "ok").put("message", "Status modificado").put("statusFactura", itemFacturación.getStatus()));
        }else{
            response.getWriter().println(new JSONObject().put("status", "error").put("message", "El numero de cuenta no existe"));
        }
    }
    
    
    public void PostZang() throws ClientProtocolException, IOException, ServletException{
//    	final SSLProtocolType protocolTypeAssistant = SSLProtocolType.TLSv1_2;
//		final SSLContext sslContextAssistant = SSLUtilityFactory
//				.createSSLContext(protocolTypeAssistant);
		String workFlow = new PartToString(request).getStringValue("workFlowName");
		String version = new PartToString(request).getStringValue("version");
		String accountSID = new PartToString(request).getStringValue("accountSID");
		String token = new PartToString(request).getStringValue("token");
		String email = new PartToString(request).getStringValue("email");
		String sms = new PartToString(request).getStringValue("sms");
		String call = new PartToString(request).getStringValue("call");
		
		String  body = "{\n"
	            + "	\"imessage\": \"{\\\"sendEmail\\\":\\\""+email+"\\\",\\\"sendSMS\\\":\\\""+sms+"\\\",\\\"makeCall\\\":\\\""+call+"\\\"}\"\n"
	            + "}";
		
		final String URI = "https://workflow.zang.io/EngagementDesignerZang/wf/Admin/createThalliumInstance/"+workFlow+"/"+version+"/"+accountSID;

		final HttpClient client = HttpClients.custom()
				//.setSSLContext(sslContextAssistant)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

		final HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Content-Type", "application/json");
		postMethod.addHeader("Authorization", "Basic " + token);

		final StringEntity ttsEntity = new StringEntity("");

		postMethod.setEntity(ttsEntity);
		final HttpResponse responseHttp = client.execute(postMethod);

		final BufferedReader inputStream = new BufferedReader(	
				new InputStreamReader(responseHttp.getEntity().getContent()));

		String line = "";
		final StringBuilder result = new StringBuilder();
		while ((line = inputStream.readLine()) != null) {
			result.append(line);
		}
		response.getWriter().println(result.toString());
    }
}
