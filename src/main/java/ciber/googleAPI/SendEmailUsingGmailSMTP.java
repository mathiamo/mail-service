package ciber.googleAPI;

/**
 * Created by matmoe on 18.03.2015.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
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
            get("/hello", SendEmailUsingGmailSMTP::handleGet);
            post("/hello", (req, res) -> "Mail sent");
        } catch (NumberFormatException e) {
            logger.warn("Exception under startup:", e);
        }
        logger.info("Finished starting Mail API");
    }

    private static String handleGet(Request request, Response response) {
        sendMail();
        return "<form>Hello World</form>";
    }

    private static void sendMail() {
        // Recipient's email ID needs to be mentioned.
        String to = "mathias.moen@ciber.com";//change accordingly
        String tre = "andywit88@gmail.com";
        ArrayList<String> emailList = new ArrayList<>();
        emailList.add(to);
        emailList.add(tre);
        // Sender's email ID needs to be mentioned
        String from = "Asdf@asdf.com";//change accordingly
        final String username = "ciberjavadevelopment@gmail.com";//change accordingly
        final String password = "development123";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
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
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            for (int i = 0; i < emailList.size(); i++) {
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(emailList.get(i)));
            }
            // Set Subject: header field
            message.setSubject("AndreasIsNotImpressed");

            // Now set the actual message
            message.setText("DO you want to play a game andreas???");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
