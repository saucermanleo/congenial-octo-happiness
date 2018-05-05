package com.example.excelexport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置在Bean属性上的Anotation
 *<p>{@code value} 设置excel头字段
 *<p>{@code ingore} 为true则忽略此字段 列子:{@link User}
 *@author Zangy
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneralExcelConfig {
	String value() default "";

	boolean ingore() default false;
}