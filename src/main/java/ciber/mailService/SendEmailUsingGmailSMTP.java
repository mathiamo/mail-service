package ciber.mailService;

/**
 * Created by matmoe on 18.03.2015.
 */

import ciber.mailService.controller.Controller;
import ciber.mailService.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class SendEmailUsingGmailSMTP {

    private static Logger logger = LoggerFactory.getLogger(SendEmailUsingGmailSMTP.class);

    public static void main(String[] args) {

        logger.info("Starting Mail API");

        try {
            MailService mailService = new MailService();
            Controller controller = new Controller(mailService);

            port(Integer.parseInt(System.getenv("PORT")));
            before((request, response) -> response.type("application/json"));

            post("/sendMail", controller::sendMail);

        }
        catch (Exception e) {
            logger.error("Exception under startup:", e);
        }
        logger.info("Finished starting Mail API");
    }
}
