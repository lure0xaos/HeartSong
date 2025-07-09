package gargoyle.heartsong.model.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import gargoyle.heartsong.ser.ObjectMapperProvider;
import lombok.Getter;
import lombok.Setter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Setter
@Getter
public class SearchResult<T> implements Externalizable {
    private static final long serialVersionUID = 8364559827686076061L;
    private transient T item;
    private Class<T> type;
    private String label;
    private String link;


    public SearchResult() {
    }

    public SearchResult(T item, Class<T> type, String label, String link) {
        this.item = item;
        this.type = type;
        this.label = label;
        this.link = link;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ObjectMapper objectMapper = ObjectMapperProvider.getObjectMapper();
        objectMapper.writeValue(out, type);
        objectMapper.writeValue(out, label);
        objectMapper.writeValue(out, link);
        objectMapper.writeValue(out, item);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readExternal(ObjectInput in) throws IOException {
        ObjectMapper objectMapper = ObjectMapperProvider.getObjectMapper();
        type = objectMapper.readValue(in, Class.class);
        label = objectMapper.readValue(in, String.class);
        link = objectMapper.readValue(in, String.class);
        item = objectMapper.readValue(in, type);
    }

}
