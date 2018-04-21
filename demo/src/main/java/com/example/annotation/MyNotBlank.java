package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.util.StringUtils;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyNotBlank.MyConstraintValidator.class)
public @interface MyNotBlank {
	
	String message() default "不能為空";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	class MyConstraintValidator implements ConstraintValidator<MyNotBlank,String>{


		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			return !StringUtils.isEmpty(value);
		}
		
	}
}
