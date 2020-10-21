package com.ritchieeinstein.falcon.messageingester.application.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampValidator implements ConstraintValidator<TimestampConstraint, String> {

    private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampValidator.class);

    @Override
    public void initialize(TimestampConstraint constraintAnnotation) {
        timestampFormat.setLenient(false);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try{
            timestampFormat.parse(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
