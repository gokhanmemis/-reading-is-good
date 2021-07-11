package com.getir.readingisgood.validation.servicesvalidator;

import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.util.ExceptionUtil;
import com.getir.readingisgood.util.GetirException;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import com.getir.readingisgood.validation.GetirValidator;
import org.aspectj.lang.JoinPoint;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerValidation implements GetirValidator {
    @Override
    public void validate(JoinPoint joinPoint) throws GetirException {
        Object[] args = joinPoint.getArgs();
        Customer cust = (Customer) args[0];
        List<String> messageCodeEnums = new ArrayList<>();
        if(ObjectUtils.isEmpty(cust)){
            ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_CUSTOMER_EMPTY_OBJECT.getValue());
        } else {
            if(ObjectUtils.isEmpty(cust.getEmail())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_CUSTOMER_EMAIL_EMPTY_OBJECT.getValue());
            }
            if(ObjectUtils.isEmpty(cust.getFirstName())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_CUSTOMER_FIRST_NAME_EMPTY_OBJECT.getValue());
            }
            if(ObjectUtils.isEmpty(cust.getLastName())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_CUSTOMER_LAST_NAME_EMPTY_OBJECT.getValue());
            }
            if(!ObjectUtils.isEmpty(cust.getEmail()) && !cust.getEmail().contains("@")){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_CUSTOMER_EMAIL_WRONG_OBJECT.getValue());
            }
        }

        ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);
    }
}
