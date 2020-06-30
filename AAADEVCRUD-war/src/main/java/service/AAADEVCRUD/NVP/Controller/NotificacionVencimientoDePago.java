package service.AAADEVCRUD.NVP.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import service.AAADEVCRUD.NVP.Acciones.BorrarNotificacion;
import service.AAADEVCRUD.NVP.Acciones.CrearNotificacion;
import service.AAADEVCRUD.NVP.Acciones.ModificarNotificacion;
import service.AAADEVCRUD.NVP.Bean.Notificacion;
import service.AAADEVCRUD.NVP.Bean.UserBean;
import service.AAADEVCRUD.NVP.Dao.NotificacionDeVencimientoDao;
import service.AAADEVCRUD.NVP.Util.PartToString;


/**
 *
 * @author umansilla
 */
@MultipartConfig
@WebServlet(name = "NotificacionVencimientoDePago", urlPatterns = {"/NotificacionVencimientoDePago"})
public class NotificacionVencimientoDePago extends BaseController {

    /**
	 * 
	 */
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
			request.setAttribute("Notificaciones", new NotificacionDeVencimientoDao().obtenerTodas());
//			ArrayList<Notificacion> prueba = new ArrayList<Notificacion>();
//			Notificacion notif = new Notificacion(123456, "Jose", "martinez", "12345", "12345","contoso@gmil.com", "10000000", "1001", "2020-10-10", 1);
//			prueba.add(notif);
//			request.setAttribute("Notificaciones", prueba);
			request.getRequestDispatcher("NVP/index.jsp").forward(request, response);			
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {

            setAccessControlHeaders(response);
            response.setContentType("application/json");
            JSONObject json = new JSONObject();
            String action = new PartToString().getStringValue(request.getPart("action"));
            switch (action) {
                case "editNotificacion":
                    json = new ModificarNotificacion(request).modificar();
                    break;
                case "crearNotificacion":
                    json = new CrearNotificacion(request).crearNotificacion();
                    break;
                case "borrarNotificacion":
                    json = new BorrarNotificacion(request).borrarNotificacion();
                    break;
            }
            out.println(json);
        } catch (IOException | ServletException e) {
            System.out.println("Error Do Post : " + e.getMessage());
            out.println(new JSONObject().put("status", "error"));
        }
    }
}
