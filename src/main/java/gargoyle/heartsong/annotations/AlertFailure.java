package gargoyle.heartsong.annotations;

import gargoyle.heartsong.model.alerts.AlertType;

public @interface AlertFailure {
    AlertType type() default AlertType.ERROR;

    String message() default "";

    String description() default "";

    boolean dismissible() default true;

    boolean raw() default false;

    boolean rethrow() default false;

    String returning() default "";
}
