package ciber.mailService.service;

import ciber.mailService.dto.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by bjerke.
 */
public class MailService {

    private static Logger logger = LoggerFactory.getLogger(MailService.class);

    public void sendMail(Mail mail){

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        // Get the Session object.
        Session session = Session.getInstance(props, getAuthenticator());

        try {
            Message message = new MimeMessage(session);
            String recipients = mail.getReceivers().stream().collect(Collectors.joining(","));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            message.setSubject(mail.getSubject());
            message.setText(mail.getBody());
            Transport.send(message);
            logger.info(String.format("Sent mail with \"subject\" %s to recipients: \"%s\"", mail.getSubject(), recipients));
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("SMTP_USERNAME"), System.getenv("SMTP_PASSWORD"));
            }
        };
    }
}
