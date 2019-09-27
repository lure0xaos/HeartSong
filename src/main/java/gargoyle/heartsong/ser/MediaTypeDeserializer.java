package gargoyle.heartsong.ser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.http.MediaType;

import java.io.IOException;

public class MediaTypeDeserializer extends StdDeserializer<MediaType> {
    private static final long serialVersionUID = -6726047373839838145L;

    public MediaTypeDeserializer() {
        super(MediaType.class);
    }

    @Override
    public MediaType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return MediaType.valueOf(p.getValueAsString());
    }
}
