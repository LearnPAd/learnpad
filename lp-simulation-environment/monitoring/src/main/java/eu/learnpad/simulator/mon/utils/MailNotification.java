package eu.learnpad.simulator.mon.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.net.ntp.TimeStamp;

public class MailNotification {

	private static Properties mailSettings;
	private static javax.mail.Authenticator auth;
	private static javax.mail.Session session;
	private static javax.mail.Message message;
	
	public MailNotification(Properties mailSettings) {
		MailNotification.mailSettings = mailSettings;
	}
	
	public static void NotifyToAdministrator(
			String ruleMatched,
			String violationResponse) {

		  try {
			  session = Session.getInstance(mailSettings,auth);
			  message = new MimeMessage(session);
			  
			  message.setFrom(new InternetAddress(
					  "antonello.calabro@isti.cnr.it"));
			  
			  System.out.println(mailSettings.getProperty("notificationRecipient"));
			  message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(mailSettings.getProperty("notificationRecipient")));
			  
			  message.setSubject("Violation");
			  message.setText("Rule violated: " + ruleMatched + "\n" +
					  	"Response: " + violationResponse);
			  
			  Transport.send(message);
			  DebugMessages.println(
					  TimeStamp.getCurrentTime(),
					  MailNotification.class.getSimpleName(),
					  "\nRule Violation\nNotification sent to the admin\n\n");

			} catch(MessagingException fail) {
				System.out.println("Authentication failed\n" + fail.getMessage());
			}
		  }

	public void start() {
		DebugMessages.print(TimeStamp.getCurrentTime(), this.getClass().getSimpleName(), "Creating MailNotifier component ");
		auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						mailSettings.getProperty("mail.smtp.user"),
						mailSettings.getProperty("mail.smtp.password"));
			}			
		  };
			DebugMessages.ok();
			DebugMessages.line();
	}
	
	public static void main (String[] args) {
		
	}
}
