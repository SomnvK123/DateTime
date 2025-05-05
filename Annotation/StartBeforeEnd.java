package Annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StartBeforeEnd {
    String message() default "Start time must be before end time.";
}
