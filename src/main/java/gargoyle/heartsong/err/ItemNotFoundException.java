package gargoyle.heartsong.err;

import java.text.MessageFormat;

public class ItemNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5045435836066246406L;

    public ItemNotFoundException(String type, Object id) {
        this(type, id, null);
    }

    public ItemNotFoundException(String type, Object id, Throwable e) {
        super(MessageFormat.format("{0} {1} not found", type, id), e);
    }
}
