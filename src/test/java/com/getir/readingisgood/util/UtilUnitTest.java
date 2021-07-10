package com.getir.readingisgood.util;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.validation.MethodValidator;
import com.getir.readingisgood.validation.OrderValidation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
public class UtilUnitTest {

    @Autowired
    MethodValidator methodValidator;

    @Test
    void messageEnumValueTest(){
        assertEquals(MessageCodeEnum.INFO.getValue(),"INFO");
    }

    @Test
    void messageEnumIdTest(){
        assertEquals(MessageCodeEnum.INFO.getId(),1);
    }


    @Test
    void messageListValueTest(){
        assertEquals(MessageListEnum.INFO_CUSTOMER_LISTED.getValue(),"Customers' information successfully are listed.");
    }

    @Test
    void messageListValueTest2(){
        assertEquals(MessageListEnum.INFO_BOOK_LISTED.getValue(),"Books' information successfully are listed.");
    }

    @Test
    void exceptionUtilAddMessageUnitTest(){
        List<String> messageCodeEnums = new ArrayList<>();
        ExceptionUtil.addToMessageList(messageCodeEnums, "Test");
        assertEquals(messageCodeEnums.size(),1);
    }

    @Test
    void exceptionUtilThworIfAnyUnitTest(){
        List<String> messageCodeEnums = new ArrayList<>();
        boolean exception = false;
        try{
            ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);
        }
        catch (Exception ex){
            exception = true;
        }

        assertEquals(exception,false);
    }

    @Test
    void methodValidatorUnitTest(){
        assertEquals(methodValidator.getOrder(),500);
    }
    @Test
    void orderValidatorUnitTest(){
        Order orderObject = new Order();
        Object[] jointArgument = {orderObject};
        OrderValidation ov = new OrderValidation();
        JoinPoint joinPoint = new JoinPoint() {
            @Override
            public String toShortString() {
                return null;
            }

            @Override
            public String toLongString() {
                return null;
            }

            @Override
            public Object getThis() {
                return null;
            }

            @Override
            public Object getTarget() {
                return null;
            }

            @Override
            public Object[] getArgs() {
                return jointArgument;
            }

            @Override
            public Signature getSignature() {
                return null;
            }

            @Override
            public SourceLocation getSourceLocation() {
                return null;
            }

            @Override
            public String getKind() {
                return null;
            }

            @Override
            public StaticPart getStaticPart() {
                return null;
            }
        };

        boolean exception = false;
        try{
            ov.validate(joinPoint);
        }
        catch (Exception ex){
            exception = true;
        }

        assertEquals(exception,true);
    }

}
