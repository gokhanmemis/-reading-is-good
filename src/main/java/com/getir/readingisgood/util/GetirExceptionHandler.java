package com.getir.readingisgood.util;

import com.getir.readingisgood.log.LogUtil;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GetirExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleExceptions(RuntimeException exception, WebRequest request) {

        if(exception instanceof GetirException){
            GetirException getirException = (GetirException) exception;
            LogUtil.exceptionLog(exception, getirException.getMessageList(), MessageCodeEnum.ERROR, request);

            if(getirException.getMessageCodeEnum().equals(MessageCodeEnum.ERROR.getValue())){
                return new ResponseEntity<>(new GetirResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, getirException.getMessageCodeEnum(), getirException.getMessageList(), null), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new GetirResponse<>(HttpStatus.OK, getirException.getMessageCodeEnum(), getirException.getMessageList(), null), HttpStatus.OK);
        }
        if(exception instanceof BadSqlGrammarException){
            String sqlExceptionMessage = "Bad sql grammar exception is available";
            LogUtil.exceptionLog(exception, Arrays.asList(sqlExceptionMessage), MessageCodeEnum.ERROR, request);

            return new ResponseEntity<>(new GetirResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, MessageCodeEnum.ERROR, Arrays.asList(sqlExceptionMessage), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LogUtil.exceptionLog(exception, Arrays.asList(exception.getMessage()), MessageCodeEnum.ERROR, request);
        return new ResponseEntity<>(new GetirResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, MessageCodeEnum.ERROR, Arrays.asList(exception.getMessage()), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
