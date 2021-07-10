package com.getir.readingisgood.validation;

import org.aspectj.lang.JoinPoint;

public interface GetirValidator {

    void validate(JoinPoint joinPoint) throws Exception;

}
