package ciber.mailService.controller;

import ciber.mailService.dto.Mail;
import ciber.mailService.service.MailService;
import ciber.mailService.util.ConversionUtil;
import spark.Request;
import spark.Response;

/**
 * Created by bjerke.
 */
public class Controller {
    private MailService mailService;

    public Controller(MailService mailService) {
        this.mailService = mailService;
    }

    public String sendMail(Request request, Response response) {
        String body = request.body();
        Mail mail = ConversionUtil.convertJsonToMail(body);
        mailService.sendMail(mail);
        return "{\"status\": \"success\"}";
    }
}
