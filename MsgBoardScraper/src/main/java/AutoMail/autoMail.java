package AutoMail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class autoMail {
	
	public static void autoMail(String mailcontent) throws EmailException {
		System.out.println("Start sending");
		
		Email email = new SimpleEmail();
		email.setHostName("mail.gmx.net");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("mkTesting@gmx.de", "mkTesting!23"));
		email.setSSLOnConnect(true);
		email.setFrom("mkTesting@gmx.de");
		email.setSubject("Deine Noten");
		email.setMsg(mailcontent);
		email.addTo("maxx_der_held@web.de");
		email.send();
		
		System.out.println("Send");
		
	}

}
