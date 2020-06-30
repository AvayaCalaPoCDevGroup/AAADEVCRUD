package service.AAADEVCRUD.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.avaya.collaboration.ssl.util.SSLUtilityException;

import service.AAADEVCRUD.Actions.NotificacionVencimientoDePagoCloudAction;
import service.AAADEVCRUD.NVP.Bean.UserBean;
import service.AAADEVCRUD.NVP.Controller.BaseController;
import service.AAADEVCRUD.Service.FacturacionImpl;
import service.AAADEVCRUD.util.Constants;
import service.AAADEVCRUD.util.PartToString;

/**
 *
 * @author umansilla
 */
@MultipartConfig
@WebServlet(name = "NVP", urlPatterns = {"/NVPZangCloud"})
public class NVP extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		setAccessControlHeaders(response);
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null) {
			removeCookie(request, response);
			request.getRequestDispatcher("/LogIn/").forward(request, response);
		} else {
			request.setAttribute("Usuarios", new FacturacionImpl().ontenerTodosLosRegistros());
			request.getRequestDispatcher("/NVP_ZANG/index.jsp").forward(request, response);		
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccessControlHeaders(response);
        switch (new PartToString(request).getStringValue("action")) {
            case "changeStatus": {
                try {
                    new NotificacionVencimientoDePagoCloudAction(request, response).changeStatus();
                } catch (Exception ex) {
                    response.getWriter().println(new JSONObject().put("status", "error").put("message", ex.toString()));
                }
            }	
            break;
            case "postZang":
			try {
				new NotificacionVencimientoDePagoCloudAction(request, response).PostZang();
			} catch (Exception e) {
				 response.getWriter().println(new JSONObject().put("status", "error").put("message", e.toString()));
			}
            	break;
            default:
                response.getWriter().println(new JSONObject().put("status", "error").put("message", "Bad Request"));
                break;
        }
    }
}
