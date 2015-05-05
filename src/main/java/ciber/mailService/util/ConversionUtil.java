package ciber.mailService.util;

import ciber.mailService.dto.Mail;
import com.google.gson.Gson;

/**
 * Created by bjerke.
 */
public class ConversionUtil {
    public static Mail convertJsonToMail(String json ){
        return new Gson().fromJson(json, Mail.class);
    }
}
