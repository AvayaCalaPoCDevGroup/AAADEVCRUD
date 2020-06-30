/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.AAADEVCRUD;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import service.AAADEVCRUD.NVP.Bean.UserBean;
import service.AAADEVCRUD.NVP.Controller.BaseController;
import service.AAADEVCRUD.modelo.encuestasatisfaccion;
import service.AAADEVCRUD.util.conexion;

/**
 *
 * @author umansilla
 */
@WebServlet(name = "Encusta", urlPatterns = { "/ControlDeEncuesta" })
public class Encusta extends BaseController {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		setAccessControlHeaders(response);
		HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
		UserBean userBeanSession = (UserBean) session.getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
		// USERBEAN
		if (userBeanSession == null) {
			removeCookie(request, response);
			request.getRequestDispatcher("/LogIn/").forward(request, response);
		} else {			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			conexion conn = new conexion();
			try {
				Connection conexion = conn.conn();
				String SQL = "SELECT * FROM encuestasatisfaccion ORDER BY id;";
				Statement stmt = conexion.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				List<encuestasatisfaccion> list = new LinkedList<>();
				while (rs.next()) {
					encuestasatisfaccion user = new encuestasatisfaccion();
					user.setId(rs.getInt("id"));
					user.setApiversion(rs.getString("apiversion"));
					user.setSmssid(rs.getString("smssid"));
					user.setSmsstatus(rs.getString("smsstatus"));
					user.setFromstring(rs.getString("fromstring"));
					user.setTostring(rs.getString("tostring"));
					user.setQualification(rs.getString("qualification"));
					user.setDate(rs.getString("datestring"));
					user.setComentarios(rs.getString("comentarios"));
					list.add(user);
				}
				Gson jsonGson = new GsonBuilder().setPrettyPrinting().create();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.setStatus(200);
				out.println(jsonGson.toJson(list));
				conexion.close();
				stmt.close();
				rs.close();
			} catch (Exception e) {
				JSONObject jsonError = new JSONObject();
				jsonError.put("Error", e.toString());
				out.println(jsonError);
				response.setStatus(400);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
		while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
		JSONObject json = new JSONObject(buffer.toString());
		String apiversion = (json.has("apiversion")?(json.getString("apiversion")):("empty"));
		String smssid = (json.has("smssid")?(json.getString("smssid")):("empty"));
		String smsstatus = (json.has("smsstatus")?(json.getString("smsstatus")):("empty"));
		String fromstring = (json.has("fromstring")?(json.getString("fromstring")):("empty"));
		String tostring = (json.has("tostring")?(json.getString("tostring")):("empty"));
		String qualification = (json.has("qualification")?(json.getString("qualification")):("empty"));
		String datestring = (json.has("datestring")?(json.getString("datestring")):("empty"));
		String comentarios = (json.has("comentarios")?(json.getString("comentarios")):("empty"));
		JSONObject jsonResponse = new JSONObject();
		try{
			updateSMSRecibe(apiversion, smssid, smsstatus, fromstring, tostring, qualification, datestring, comentarios);
			jsonResponse.put("status", "ok");
			response.setStatus(200);
		}catch(Exception e){
			jsonResponse.put("status", "error " + e.toString());
			response.setStatus(400);
		}
		out.println(jsonResponse);
	}

	
    public void updateSMSRecibe(String apiversion, String smssid, String smsstatus, String fromstring, String tostring, String qualification, String datestring, String comentarios) throws SQLException, Exception {
        conexion conn = new conexion();
        Connection conexion = conn.conn();
        long idResponse = 0;
        Statement stmt = conexion.createStatement();
        int affectedRows = stmt.executeUpdate("INSERT INTO encuestasatisfaccion(apiversion, smssid, smsstatus, fromstring, tostring, qualification, datestring, comentarios)"
        				+" VALUES ('"+apiversion+"', '"+smssid+"', '"+smsstatus+"', '"+fromstring+"', '"+tostring+"', '"+qualification+"', '"+datestring+"', '"+comentarios+"');");
        
        /*EVALUA SI SE REALIZÓ EL INSERT*/
        if (affectedRows > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idResponse = rs.getLong(1);
            }
        } else {
            throw new Exception("No se ha realizado el update");
        }
    }
}
