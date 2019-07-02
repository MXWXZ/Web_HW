package com.imwxz.store.util.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserToken {
    boolean required() default true;

    boolean verifyId() default true;

    boolean adminFetch() default true;

    boolean jsonBody() default false;
}
