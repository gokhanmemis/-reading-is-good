package com.getir.readingisgood.util;

import java.util.List;

public class GetirException extends RuntimeException {

    private MessageCodeEnum messageCodeEnum;
    private List<String> messageList;

    public GetirException(GetirExceptionBuilder getirExceptionBuilder){
        super();
        this.messageCodeEnum = getirExceptionBuilder.messageCodeEnum;
        this.messageList = getirExceptionBuilder.messageList;
    }

    public static class GetirExceptionBuilder {

        private MessageCodeEnum messageCodeEnum;
        private List<String> messageList;

        public GetirExceptionBuilder(MessageCodeEnum messageCodeEnum){
            this.messageCodeEnum = messageCodeEnum;
        }

        public GetirExceptionBuilder messageCodeEnum(MessageCodeEnum messageCodeEnum){
            this.messageCodeEnum = messageCodeEnum;
            return this;
        }

        public GetirExceptionBuilder messageList(List<String> messageList){
            this.messageList = messageList;
            return this;
        }

        public GetirException build(){
            GetirException getirException = new GetirException(this);
            return getirException;
        }

    }

    public MessageCodeEnum getMessageCodeEnum() {
        return messageCodeEnum;
    }

    public List<String> getMessageList() {
        return messageList;
    }
}
