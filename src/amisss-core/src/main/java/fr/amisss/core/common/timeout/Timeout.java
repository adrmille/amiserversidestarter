package fr.amisss.core.common.timeout;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicate that the method must finish before the initial amount of time is spent.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Timeout {

    /**
     * @return maximum time (in millisecond) of execution for the annotated method
     */
    long timeoutMilli();
    
}
