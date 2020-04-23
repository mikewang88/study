package com.aboutspring.sourcecode.config.annotation;

/**
 * @Author: MikeWang
 * @Date: 2020/4/19 10:10 PM
 * @Description:
 */
import com.aboutspring.sourcecode.beans.BeanDefinition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";

    String name() default "";

    String scope() default BeanDefinition.SCOPE_SINGLETION;

    String factoryMethodName() default "";

    String factoryBeanName() default "";

    String initMethodName() default "";

    String destroyMethodName() default "";
}
