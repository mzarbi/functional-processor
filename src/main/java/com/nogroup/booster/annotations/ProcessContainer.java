package com.nogroup.booster.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.nogroup.booster.utils.EndPoint;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ProcessContainer {
	Class<?> next() default EndPoint.class;
	String label() default "" ;
}
