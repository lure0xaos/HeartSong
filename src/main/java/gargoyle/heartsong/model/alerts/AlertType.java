package gargoyle.heartsong.model.alerts;

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

    public String getCssClass() {
        return cssClass;
    }
}
