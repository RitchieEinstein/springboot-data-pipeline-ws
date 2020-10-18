package com.ritchieeinstein.falcon.messageingester.application.rest.controller.advice;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleMethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> errorList.add("'" + fieldError.getField() + "' " + fieldError.getDefaultMessage() +" : rejected value [" +fieldError.getRejectedValue() +"]" ));
        result.getGlobalErrors().forEach((fieldError) -> errorList.add(fieldError.getObjectName()+" : " +fieldError.getDefaultMessage() ));

        return new Error(HttpStatus.BAD_REQUEST, errorList);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errorList = new ArrayList<>();
        ex.getConstraintViolations().stream().findAny().get().getPropertyPath();
        ex.getConstraintViolations().stream().forEach(constraintViolation -> errorList.add("'" + constraintViolation.getPropertyPath()
                + "'" + constraintViolation.getMessage() + " : rejected value [" + constraintViolation.getInvalidValue() + "]"));

        return new Error(HttpStatus.BAD_REQUEST, errorList);
    }


    public static class Error{
        private int errorCode;
        private String error;
        private List<String> errorFields;

        public Error(HttpStatus status, List<String> errorFields) {
            this.errorCode = status.value();
            this.error = status.name();
            this.errorFields = errorFields;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public List<String> getErrorFields() {
            return errorFields;
        }

        public void setErrorFields(List<String> errorFields) {
            this.errorFields = errorFields;
        }
    }
}
