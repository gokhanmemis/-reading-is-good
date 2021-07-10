package com.getir.readingisgood.util;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class ExceptionUtil {

    protected ExceptionUtil(){
        throw new UnsupportedOperationException();
    }

    public static void addToMessageList(List<String> messageList, String message){
        if(!messageList.contains(message)){
            messageList.add(message);
        }
    }

    public static void throwIfAny(List<String> messageList, MessageCodeEnum messageCodeEnum){
        if(!CollectionUtils.isEmpty(messageList)){
            throw new GetirException.GetirExceptionBuilder(messageCodeEnum).messageList(messageList).build();
        }
    }

}
