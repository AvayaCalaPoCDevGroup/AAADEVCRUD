package service.AAADEVCRUD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import service.AAADEVCRUD.util.SendEmail;

/**
 * Servlet implementation class sendEmail
 */
@WebServlet(name = "sendEmail", urlPatterns = { "/SendEmail" })
public class sendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sendEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		JSONObject jsonRequest = new JSONObject(buffer.toString());
		JSONObject jsonResponse = new JSONObject();
		try {
			
			String sender = jsonRequest.getString("sender");
			String to = jsonRequest.getString("to");
			String cc = (jsonRequest.has("cc")) ? (jsonRequest.getString("cc")): ("empty");
			String bcc = (jsonRequest.has("bcc")) ? (jsonRequest.getString("bcc")) : ("empty");
			String emailSubject = jsonRequest.getString("emailSubject");
			String emailBody = jsonRequest.getString("emailBody");
			

			SendEmail email = new SendEmail();
			email.sendEmail(sender, to, cc, bcc, emailSubject, emailBody);
			jsonResponse.put("status", "ok");
			response.setStatus(200);
		} catch (Exception e) {
			jsonResponse.put("status", "Error" + e.toString());
			response.setStatus(400);
		}
		out.println(jsonResponse);
	}

}
