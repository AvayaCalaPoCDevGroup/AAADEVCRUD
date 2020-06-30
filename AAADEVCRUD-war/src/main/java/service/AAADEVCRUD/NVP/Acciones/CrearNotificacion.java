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
public class CrearNotificacion {

    private final HttpServletRequest request;

    public CrearNotificacion(HttpServletRequest request) {
        this.request = request;
    }

    public JSONObject crearNotificacion() throws IOException, ServletException {
        NotificacionDeVencimientoDao dao = new NotificacionDeVencimientoDao();
        Notificacion notificacionCount = dao.obtenerCuentaDeNotificaciones();
        int numeroDeCuenta = notificacionCount.getCountNotificaciones() + 1;
        Notificacion notificacion = new Notificacion(
                numeroDeCuenta,
                new PartToString().getStringValue(request.getPart("nombre")),
                new PartToString().getStringValue(request.getPart("apellido")),
                new PartToString().getStringValue(request.getPart("telefonocasa")),
                new PartToString().getStringValue(request.getPart("telefonomovil")),
                new PartToString().getStringValue(request.getPart("email")),
                new PartToString().getStringValue(request.getPart("monto")),
                new PartToString().getStringValue(request.getPart("numerodefactura")),
                new PartToString().getStringValue(request.getPart("fechadevencimiento")),
                Integer.parseInt(new PartToString().getStringValue(request.getPart("status"))));

        return new NotificacionDeVencimientoDao().crearNotificacion(notificacion);
    }
}
