package com.itheima.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.itheima.validation.StateValidation;

import java.lang.annotation.ElementType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { StateValidation.class })
public @interface State {
	String message() default "state 必须是 已发布 | 草稿";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
