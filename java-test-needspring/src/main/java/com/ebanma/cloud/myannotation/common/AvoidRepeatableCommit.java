package com.ebanma.cloud.myannotation.common;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AvoidRepeatableCommit {
    long timeout() default 3;
}
