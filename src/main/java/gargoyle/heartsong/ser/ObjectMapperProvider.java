package gargoyle.heartsong.ser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.http.MediaType;

public final class ObjectMapperProvider {
    private ObjectMapperProvider() {
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(MediaType.class, new MediaTypeSerializer());
        module.addDeserializer(MediaType.class, new MediaTypeDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
