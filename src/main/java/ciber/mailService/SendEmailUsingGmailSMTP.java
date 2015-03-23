package ciber.mailService;

/**
 * Created by matmoe on 18.03.2015.
 */

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import static spark.Spark.*;

public class SendEmailUsingGmailSMTP {
    private static Logger logger = LoggerFactory.getLogger(SendEmailUsingGmailSMTP.class);



    public static void main(String[] args) {
        logger.info("Starting Mail API");

        try {
            port(Integer.parseInt(System.getenv("PORT")));
            before((request, response) -> response.type("application/json"));
            get("/", (request, response) -> {
                logger.info("Finished starting Mail API");
                return "hello world";
            });

            post("/sendMail", (Request request, Response response) -> {
                String body = request.body();
                Mail mail = getMailObjectFromResponse(body);
                sendMail(mail);
                return "Mail sent!";
            });

        } catch (NumberFormatException e) {
            logger.warn("Exception under startup:", e);
        }
        logger.info("Finished starting Mail API");
    }

    private static  Mail getMailObjectFromResponse(String json ){
        return new Gson().fromJson(json, Mail.class);
    }



    private static void sendMail(Mail mail) {
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
