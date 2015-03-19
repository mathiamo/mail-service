package ciber.googleAPI;
import spark.ResponseTransformer;
import com.google.gson.*;
/**
 * Created by matmoe on 19.03.2015.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
