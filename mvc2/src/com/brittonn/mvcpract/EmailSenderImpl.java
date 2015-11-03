package com.brittonn.mvcpract;

import java.io.FileReader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements EmailSender {

	@Override
	public boolean sendEmail(String toAddress, String toName, String subject, String message) {
		Properties props = new Properties();
		
        
        try {
        	props.load(new FileReader( "f:\\dlmvc\\email.properties"));
            Session	 session = Session.getDefaultInstance(props, null);
    		    Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(props.getProperty("email.from.email"), props.getProperty("email.from.name")));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(toAddress, toName));
            msg.setSubject(subject);
            msg.setText(message);
            
            Transport tr = session.getTransport("smtp");
            tr.connect(props.getProperty("email.smpt.host"),
            		props.getProperty("email.smpt.user"),
            		props.getProperty("email.smpt.password"));
            msg.saveChanges();      
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
	}
	
	public static void main(String[] args) {
		new EmailSenderImpl().sendEmail("dltest@snowjest.plus.com", "A test user", "this is a test", "Here is some test text");
	}

}
