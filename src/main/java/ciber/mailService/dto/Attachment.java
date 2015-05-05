package ciber.mailService.dto;

/**
 * Created by bjerke.
 */
public class Attachment {
    String encodedFile;
    String filename;
    String mimeType;

    public String getEncodedFile() {
        return encodedFile;
    }

    public String getFilename() {
        return filename;
    }

    public String getMimeType() {
        return mimeType;
    }
}
