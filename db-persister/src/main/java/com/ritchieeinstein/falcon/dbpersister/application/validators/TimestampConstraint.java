package com.ritchieeinstein.falcon.dbpersister.application.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = TimestampValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimestampConstraint {

    String message() default "must be valid format (yyyy-MM-dd HH:mm:ssZ) ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
