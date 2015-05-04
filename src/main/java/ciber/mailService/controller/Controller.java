package ciber.mailService.controller;

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
        try{
            mailService.sendMail(ConversionUtil.convertJsonToMail(request.body()));
            response.status(200);
            return "{\"status\": \"success\"}";
        }
        catch(Exception e) {
            response.status(500);
            return "{\"error\": \""+ e.getMessage() +"\"}";
        }
    }
}
