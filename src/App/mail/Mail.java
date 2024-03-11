package App.mail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Troanh
 */
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail
{

    public Mail() {
    }
    

	//SETUP MAIL SERVER PROPERTIES
	//DRAFT AN EMAIL
	//SEND EMAIL
		
	Session newSession = null;
	MimeMessage mimeMessage = null;
//	public static void main(String args[]) throws AddressException, MessagingException, IOException
//	{
//		Mail mail = new Mail();
//		mail.setupServerProperties();
//		mail.draftEmail();
//		mail.sendEmail();
//	}

	public void sendEmail() throws MessagingException {
		String fromUser = "Troanh1508@gmail.com";  //Enter sender email id
		String fromUserPassword = "vsqu rzlv qrsc xbgb";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email successfully sent!");
	}

	public MimeMessage draftEmail(String recepient, String attachment) throws AddressException, MessagingException, IOException {
		String[] emailReceipients = {recepient};  //Enter list of email recepients
		String emailSubject = "Cảnh báo học vụ";
		mimeMessage = new MimeMessage(newSession);
		
		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);
	   
      // CREATE MIMEMESSAGE 
	    // CREATE MESSAGE BODY PARTS 
	    // CREATE MESSAGE MULTIPART 
	    // ADD MESSAGE BODY PARTS ----> MULTIPART 
	    // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object 
	    
	    
		 MimeBodyPart bodyPart = new MimeBodyPart();
		 bodyPart.setText("Bạn cần cố gắng hơn trong học tập");
                 
                 MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                 attachmentBodyPart.attachFile(new File(attachment));
                 
		 MimeMultipart multiPart = new MimeMultipart();
		 multiPart.addBodyPart(bodyPart);
                 multiPart.addBodyPart(attachmentBodyPart);
		 mimeMessage.setContent(multiPart);
		 return mimeMessage;
	}

	public void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties,null);
	}
        
        
	
}	