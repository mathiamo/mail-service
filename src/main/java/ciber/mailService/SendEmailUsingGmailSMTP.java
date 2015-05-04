package ciber.mailService;

/**
 * Created by matmoe on 18.03.2015.
 */

import ciber.mailService.dto.Mail;
import ciber.mailService.service.MailService;
import ciber.mailService.util.ConversionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class SendEmailUsingGmailSMTP {
    private static Logger logger = LoggerFactory.getLogger(SendEmailUsingGmailSMTP.class);
    public static void main(String[] args) {
        logger.info("Starting Mail API");

        try {
            port(Integer.parseInt(System.getenv("PORT")));
            before((request, response) -> response.type("application/json"));
            post("/sendMail", (Request request, Response response) -> {
                String body = request.body();
                Mail mail = ConversionUtil.convertJsonToMail(body);
                new MailService().sendMail(mail);
                return "Mail sent!";
            });

        } catch (NumberFormatException e) {
            logger.warn("Exception under startup:", e);
        }
        logger.info("Finished starting Mail API");
    }
}
