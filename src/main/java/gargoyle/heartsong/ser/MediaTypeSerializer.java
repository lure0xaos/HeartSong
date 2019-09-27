package gargoyle.heartsong.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.http.MediaType;

import java.io.IOException;

public class MediaTypeSerializer extends StdSerializer<MediaType> {
    private static final long serialVersionUID = -2374028388196121157L;

    public MediaTypeSerializer() {
        super(MediaType.class);
    }

    @Override
    public void serialize(MediaType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toString());
    }
}
