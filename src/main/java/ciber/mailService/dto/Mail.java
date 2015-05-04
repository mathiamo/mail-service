package ciber.mailService.dto;

import java.util.ArrayList;

/**
 * Created by matmoe on 19.03.2015.
 */
public class Mail {

    String subject;
    String body;
    ArrayList<String> receivers = new ArrayList<>();

    public Mail(String subject, String body, ArrayList<String>receivers) {
        this.body=body;
        this.subject=subject;
        this.receivers=receivers;
    }
    public String getSubject() {
        return subject;
    }
    public ArrayList<String> getReceivers() {
        return receivers;
    }
    public String getBody() {
        return body;
    }
}
