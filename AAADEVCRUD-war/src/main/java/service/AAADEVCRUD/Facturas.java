package service.AAADEVCRUD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AAADEVCRUD.NVP.Bean.UserBean;
import service.AAADEVCRUD.NVP.Controller.BaseController;
import service.AAADEVCRUD.controlador.Controlador;
import service.AAADEVCRUD.modelo.facturacion;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;



/**
 * Servlet implementation class Notificaciones
 */
@WebServlet(name = "facturacion", urlPatterns = { "/ControlDefacturacion" })
public class Facturas extends BaseController {
	private static final long serialVersionUID = 1L;

	public Facturas() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null) {
			removeCookie(request, response);
			request.getRequestDispatcher("/LogIn/").forward(request, response);
		} else {			
			PrintWriter out = response.getWriter();
			try {
				if (request.getParameter("numerodecuenta") == null) {
					out.println(Controlador.showNotifications());
					response.setStatus(200);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.setStatus(200);
				}
				if (request.getParameter("numerodecuenta") != null) {
					String accountnum_string = request
							.getParameter("numerodecuenta");
					int numerodecuenta = Integer.parseInt(accountnum_string);
					response.setStatus(200);
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					out.println(Controlador
							.showNotificationByAccount(numerodecuenta));
					
				}
			} catch (Exception e) {
				/* RESPUESSTA ERROR EN FORMATO JSON */
				JsonObject error = new JsonObject();
				error.addProperty("Status", "Error");
				error.addProperty("Error", e.getMessage());
				out.println(error);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            facturacion mybean = gson.fromJson(reader, facturacion.class);
            Controlador.createNotification(mybean);
            JsonObject ok = new JsonObject();
            ok.addProperty("Status", "Ok");
            out.println(ok);
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            /*RESPUESSTA ERROR EN FORMATO JSON*/
            JsonObject error = new JsonObject();
            error.addProperty("Status", "Error");
            error.addProperty("Error", e.getMessage());
            out.println(error);
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
	}
	
	
	@Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	logger.info("Método PUT");
    	setAccessControlHeaders(response);
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            facturacion mybean = gson.fromJson(reader, facturacion.class);
            try {
                Controlador.updateNotification(mybean);
                JsonObject ok = new JsonObject();
                ok.addProperty("Status", "Ok");
                out.println(ok);
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            } catch (Exception e) {
                /*RESPUESSTA ERROR EN FORMATO JSON*/
                JsonObject error = new JsonObject();
                error.addProperty("Status", "Error");
                error.addProperty("Error", "No se ha realizado ningún cambio / No existe numero de cuenta " + mybean.getNumerodecuenta() + "");
                out.println(error);
                response.setStatus(403);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            JsonObject error = new JsonObject();
            error.addProperty("Status", "Error");
            error.addProperty("Error", e.getMessage());
            out.println(error);
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

        }
    }
	
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	logger.info("Método DELETE");
    	setAccessControlHeaders(response);
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            facturacion mybean = gson.fromJson(reader, facturacion.class);
            try {
                Controlador.deleteNotification(mybean);
                JsonObject ok = new JsonObject();
                ok.addProperty("Status", "Ok");
                out.println(ok);
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            } catch (Exception e) {
                /*RESPUESSTA ERROR EN FORMATO JSON*/
                JsonObject error = new JsonObject();
                error.addProperty("Status", "Error");
                error.addProperty("Error", "No se ha realizado ningún cambio / No existe numero de cuenta " + mybean.getNumerodecuenta() + "");
                out.println(error);
                response.setStatus(403);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            JsonObject error = new JsonObject();
            error.addProperty("Status", "Error");
            error.addProperty("Error", e.getMessage());
            out.println(error);
            response.setStatus(403);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

        }

    }

	@Override
	protected void doOptions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
