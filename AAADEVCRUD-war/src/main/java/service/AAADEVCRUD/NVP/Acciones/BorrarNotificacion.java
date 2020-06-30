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
public class BorrarNotificacion {

    private final HttpServletRequest request;

    public BorrarNotificacion(HttpServletRequest request) {
        this.request = request;
    }

    public JSONObject borrarNotificacion() throws IOException, ServletException {
        return new NotificacionDeVencimientoDao().borrarNotificacion(new Notificacion(
                Integer.parseInt(new PartToString().getStringValue(request.getPart("numerodecuenta"))),
                 new PartToString().getStringValue(request.getPart("nombre"))));
    }
}
