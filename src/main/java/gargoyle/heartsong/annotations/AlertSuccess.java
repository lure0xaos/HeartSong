package gargoyle.heartsong.annotations;

import gargoyle.heartsong.model.alerts.AlertType;

public @interface AlertSuccess {
    AlertType type() default AlertType.SUCCESS;

    String message() default "";

    String description() default "";

    boolean dismissible() default true;

    boolean raw() default false;
}
