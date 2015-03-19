package ciber.googleAPI;

/**
 * Created by matmoe on 19.03.2015.
 */
public class Mail {

    String subject;
    String body;

    public Mail(String subject, String body) {
        this.body=body;
        this.subject=subject;
    }

    public String getSubject() {
        return subject;
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
