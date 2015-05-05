package ciber.mailService.service;

import ciber.mailService.dto.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.util.Base64;
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
            String toRecipients = mail.getToRecipients().stream().collect(Collectors.joining(","));
            String ccRecipients = mail.getCcRecipients().stream().collect(Collectors.joining(","));
            String bccRecipients = mail.getBccRecipients().stream().collect(Collectors.joining(","));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toRecipients));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccRecipients));
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccRecipients));
            message.setSubject(mail.getSubject());
            message.setText(mail.getBody());
            addAttachments(message, mail);
            Transport.send(message);
            logger.info(String.format("Sent mail with \"subject\" %s to recipients: \"%s\"", mail.getSubject(), toRecipients));
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAttachments(Message message, Mail mail) throws MessagingException {

        Multipart multipart = new MimeMultipart();

        mail.getAttachments().stream().forEach(attachment -> {
            byte[] data = Base64.getDecoder().decode(attachment.getEncodedFile());
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String fileName = attachment.getFilename();
            DataSource source = new ByteArrayDataSource(data, attachment.getMimeType());
            try{
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart);
            }
            catch(Exception e){
                logger.error("Something went wrong during conversion from base64 to message");
            }
        });
        message.setContent(multipart);
    }

    private Authenticator getAuthenticator() {
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(System.getenv("SMTP_USERNAME"), System.getenv("SMTP_PASSWORD"));
            }
        };
    }
}
