package org.ybygjy.spring.c1.aop;

import java.lang.annotation.*;

/**
 * Created by leye on 2017/11/16.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LogAnnotation {
}
