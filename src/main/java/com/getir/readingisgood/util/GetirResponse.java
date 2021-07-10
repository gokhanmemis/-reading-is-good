package com.getir.readingisgood.util;

import org.springframework.http.HttpStatus;

import java.util.List;

public class GetirResponse<T> {

    private HttpStatus status;
    private MessageCodeEnum messageCode;
    private List<String> messageList;
    private T data;

    public GetirResponse(HttpStatus status, MessageCodeEnum messageCode, List<String> messageList, T data){
        this.status = status;
        this.messageCode = messageCode;
        this.messageList = messageList;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public MessageCodeEnum getMessageCode() {
        return messageCode;
    }

    public List<String> getMessageList() {
        return messageList;
    }

    public T getData() {
        return data;
    }
}
