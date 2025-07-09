package gargoyle.heartsong.model.alerts;

import lombok.Getter;

@Getter
public enum AlertType {
    NONE(""),
    ERROR("danger"),
    WARNING("warning"),
    INFO("info"),
    SUCCESS("success");

    private final String cssClass;

    AlertType(String cssClass) {
        this.cssClass = cssClass;
    }

}
