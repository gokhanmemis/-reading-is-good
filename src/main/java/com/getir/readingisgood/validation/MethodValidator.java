package com.getir.readingisgood.validation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodValidator implements Ordered {

    @Before("@annotation(validator)")
    public void validateServiceOperation(JoinPoint joinPoint, Validator validator) throws Exception {
        if(validator != null){
            Class<? extends GetirValidator> MethodValidator = validator.methodValidator();
            Object obj = MethodValidator.newInstance();
            GetirValidator actionObj = (GetirValidator) obj;
            actionObj.validate(joinPoint);
        }
    }

    @Override
    public int getOrder() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
