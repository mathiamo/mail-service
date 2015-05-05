package ciber.mailService.dto;

import java.util.ArrayList;

/**
 * Created by matmoe on 19.03.2015.
 */
public class Mail {

    String subject;
    String body;
    ArrayList<String> toRecipients = new ArrayList<>();
    ArrayList<String> ccRecipients = new ArrayList<>();
    ArrayList<String> bccRecipients = new ArrayList<>();
    ArrayList<Attachment> attachments = new ArrayList<>();

    public Mail() {}

    public Mail(String subject, String body, ArrayList<String> toRecipients, ArrayList<String> ccRecipients, ArrayList<String> bccRecipients, ArrayList<Attachment> attachments) {
        this.body=body;
        this.subject=subject;
        this.toRecipients=toRecipients;
        this.ccRecipients=ccRecipients;
        this.bccRecipients=bccRecipients;
        this.attachments=attachments;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public ArrayList<String> getToRecipients() {
        return toRecipients;
    }

    public ArrayList<String> getCcRecipients() {
        return ccRecipients;
    }

    public ArrayList<String> getBccRecipients() {
        return bccRecipients;
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }
}
