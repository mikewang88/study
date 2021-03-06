package com.aboutspring.sourcecode.config.annotation;

/**
 * @Author: MikeWang
 * @Date: 2020/4/19 10:17 PM
 * @Description:
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
