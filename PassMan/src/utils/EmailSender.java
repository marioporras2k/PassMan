package utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

		//123456.dam.78
		
		public void sendAnEmail(String email) {
		Properties p = new Properties();
		p.setProperty("un nombre", "un valor");
		p.setProperty("PI", "3.1416");
		Properties props = new Properties();

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port","587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", "passmanservice@gmail.com");

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");

	    
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("passmanservice@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			
			message.setSubject("Welcome PassMan");
			message.setText("We are exited about your recent registration,  thanks for trust us. You can write this email to get help ");
			message.setSentDate(new Date());
			
			Transport t = session.getTransport("smtp");
			t.connect("passmanservice@gmail.com","passmanservice2021");
			t.sendMessage(message,message.getAllRecipients());
			t.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
		public void emailScrapping(String email, String sitioweb) {
			Properties p = new Properties();
			p.setProperty("un nombre", "un valor");
			p.setProperty("PI", "3.1416");
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", "smtp.gmail.com");

			// TLS si está disponible
			props.setProperty("mail.smtp.starttls.enable", "true");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port","587");

			// Nombre del usuario
			props.setProperty("mail.smtp.user", "passmanservice@gmail.com");

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");
		    
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			// Quien envia el correo
			try {
				message.setFrom(new InternetAddress("passmanservice@gmail.com"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
				
				message.setSubject("Changues in your web");
				message.setText("We have detected some changues in this page:" + sitioweb);
				message.setSentDate(new Date());
				
				Transport t = session.getTransport("smtp");
				t.connect("passmanservice@gmail.com","passmanservice2021");
				t.sendMessage(message,message.getAllRecipients());
				t.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
