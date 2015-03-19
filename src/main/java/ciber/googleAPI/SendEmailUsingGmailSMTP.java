package ciber.googleAPI;

/**
 * Created by matmoe on 18.03.2015.
 */

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
    public static void main(String[] args) {
        port(Integer.parseInt(System.getenv("PORT"));
            get("/hello", (req, res) -> "<form>Hello World</form>");
            post("/hello", (req, res) -> "Mail sent");

    }

    private static void sendMail(Mail mail) {
        // Recipient's email ID needs to be mentioned.
        String to = "mathias.moen@ciber.com";//change accordingly
        String tre = "oyvind.bjerke@ciber.com";
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
            message.setSubject("Dine interesser");

            // Now set the actual message
            message.setText("Hello, this is sample for to check send "
                    + "email using JavaMailAPI ");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
