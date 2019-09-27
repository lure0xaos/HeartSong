package gargoyle.heartsong.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionAlert {
    AlertSuccess success() default @AlertSuccess;

    AlertFailure failure() default @AlertFailure;
}
