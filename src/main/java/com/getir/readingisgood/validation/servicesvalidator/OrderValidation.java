package com.getir.readingisgood.validation.servicesvalidator;

import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.util.ExceptionUtil;
import com.getir.readingisgood.util.GetirException;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import com.getir.readingisgood.validation.GetirValidator;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderValidation implements GetirValidator {

    @Override
    public void validate(JoinPoint joinPoint) throws GetirException {

        Object[] args = joinPoint.getArgs();
        Order order = (Order) args[0];

        List<String> messageCodeEnums = new ArrayList<>();
        if(ObjectUtils.isEmpty(order)){
            ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_EMPTY_OBJECT.getValue());
        } else {
            if(ObjectUtils.isEmpty(order.getBookId())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_BOOK_EMPTY_OBJECT.getValue());
            }
            if(ObjectUtils.isEmpty(order.getCustomerId())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_CUSTOMER_EMPTY_OBJECT.getValue());
            }
            if(ObjectUtils.isEmpty(order.getQuantity())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_QUANTITY_EMPTY_OBJECT.getValue());
            }
            if(ObjectUtils.isEmpty(order.getOrderDate())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_DATE_EMPTY_OBJECT.getValue());
            }
            if(!ObjectUtils.isEmpty(order.getQuantity()) && order.getQuantity().intValue() <=0){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_QUANTITY_WRONG_VALUE.getValue());
            }
        }

        ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);


    }
}
