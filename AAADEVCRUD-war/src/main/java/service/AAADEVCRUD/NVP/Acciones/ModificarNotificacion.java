package service.AAADEVCRUD.NVP.Acciones;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import service.AAADEVCRUD.NVP.Bean.Notificacion;
import service.AAADEVCRUD.NVP.Dao.NotificacionDeVencimientoDao;
import service.AAADEVCRUD.NVP.Util.PartToString;

/**
 *
 * @author umansilla
 */
public class ModificarNotificacion {

    private final HttpServletRequest request;

    public ModificarNotificacion(HttpServletRequest request) {
        this.request = request;
    }

    public JSONObject modificar() throws IOException, ServletException {
        Notificacion notificacion = new Notificacion(
                Integer.parseInt(new PartToString().getStringValue(request.getPart("numerodecuenta"))),
                new PartToString().getStringValue(request.getPart("nombre")),
                new PartToString().getStringValue(request.getPart("apellido")),
                new PartToString().getStringValue(request.getPart("telefonocasa")),
                new PartToString().getStringValue(request.getPart("telefonomovil")),
                new PartToString().getStringValue(request.getPart("eMail")),
                new PartToString().getStringValue(request.getPart("monto")),
                new PartToString().getStringValue(request.getPart("numerodefactura")),
                new PartToString().getStringValue(request.getPart("fechadevencimiento")),
                Integer.parseInt(new PartToString().getStringValue(request.getPart("status"))));

        return new NotificacionDeVencimientoDao().modificarNotificacion(notificacion);
    }
}
