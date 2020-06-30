package service.AAADEVCRUD.util;

import com.avaya.collaboration.email.EmailFactory;
import com.avaya.collaboration.email.EmailRequest;
import com.avaya.collaboration.util.logger.Logger;

public class SendEmail {
	
	private final Logger logger = Logger.getLogger(getClass());
	
	public void sendEmail(final String sender, final String to, final String cc, final String bcc, final String emailSubject, final String emailBody) throws Exception{
		final EmailRequest emailRequest = EmailFactory.createEmailRequest();
		emailRequest.getTo().add(sender);
		emailRequest.getTo().add(to);
		emailRequest.setSubject(emailSubject);
		emailRequest.setTextBody(emailBody);
		if(!cc.equals("empty")){
			emailRequest.getCc().add(cc);
		}
		if(!bcc.equals("empty")){
			emailRequest.getBcc().add(bcc);
		}
		try{
			emailRequest.send();
		}catch(Exception e){
			logger.error("Error al enviar el correo " + e.toString());
			throw new Exception("Error");
		}
		
	}
}
