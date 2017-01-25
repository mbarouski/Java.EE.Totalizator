package sport.totalizator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * {@link JsonSerializer} is used for transferring some object to JSON format.
 */
public class JsonSerializer {
    public static String json(Object obj) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
