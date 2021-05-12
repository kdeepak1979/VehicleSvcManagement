package com.innomind.vehiclesvc.mgmt.quartz;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.innomind.vehiclesvc.mgmt.entity.Customer;

/**
 * 
 * @author kdeep
 *
 */
@Component
@PropertySource("application.properties")
public class Mailer {

	private Properties mailProperties = new Properties();
	
	@Value("${smtp.host}")
	private String smtpHost;
	@Value("${smtp.port}")
	private String smtpPort;
	@Value("${smtp.user}")
	private String username;
	@Value("${smtp.password}")
	private String password;
	@Value("${from.mail}")
	private String fromMail;
	@Value("${mail.subject}")
	private String mailSubject;
	@Value("${mail.message}")
	private String mailMessage;
		

	@PostConstruct
	private void initProperties() {
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
		mailProperties.put("mail.smtp.host", smtpHost);
		mailProperties.put("mail.smtp.port", smtpPort);
	}
	
	/**
	 * 
	 * @return
	 */
	public void sendReminder(Customer cust) {
		try {
			Session session = getSession();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cust.getEmailID()));
			message.setSubject(mailSubject);

			String msg = "This is my first email using JavaMailer";

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);

			message.setContent(multipart);

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	private Session getSession() {
		Session session = Session.getInstance(mailProperties, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(username, password);
		    }
		});
		return session;
	}
	
}
