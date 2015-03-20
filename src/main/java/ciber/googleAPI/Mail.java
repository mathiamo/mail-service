package ciber.googleAPI;

import java.util.ArrayList;

/**
 * Created by matmoe on 19.03.2015.
 */
public class Mail {

    String subject;
    String body;
    String receiver;

    public Mail(String subject, String body, String receiver) {
        this.body=body;
        this.subject=subject;
        this.receiver=receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
