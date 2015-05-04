package ciber.mailService.service;

import ciber.mailService.dto.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by bjerke.
 */
public class MailService {
    public void sendMail(Mail mail){
        //TODO: add list of users based on interests

        String from = "Asdf@asdf.com";//change accordingly
        final String username = "ciberjavadevelopment@gmail.com";//change accordingly
        final String password = "development123";//change accordingly

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            for (String s : mail.getReceivers()) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(s));
                message.setSubject(mail.getSubject());
                message.setText(mail.getBody());

                Transport.send(message);
                System.out.println("Sent message successfully....");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
