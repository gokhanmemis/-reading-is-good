package com.getir.readingisgood.validation.servicesvalidator;

import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.util.ExceptionUtil;
import com.getir.readingisgood.util.GetirException;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import com.getir.readingisgood.validation.GetirValidator;
import org.aspectj.lang.JoinPoint;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class BookValidation implements GetirValidator {
    @Override
    public void validate(JoinPoint joinPoint) throws GetirException {
        Object[] args = joinPoint.getArgs();
        Book book = (Book) args[0];
        List<String> messageCodeEnums = new ArrayList<>();
        if(ObjectUtils.isEmpty(book)){
            ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_BOOK_EMPTY_OBJECT.getValue());
        } else {
            if(ObjectUtils.isEmpty(book.getBookName())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_BOOK_NAME_EMPTY_OBJECT.getValue());
            }
            if(ObjectUtils.isEmpty(book.getAuthor())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_BOOK_AUTHOR_EMPTY_OBJECT.getValue());
            }
            if(ObjectUtils.isEmpty(book.getIsbn())){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_BOOK_ISBN_EMPTY_OBJECT.getValue());
            }
            if(!ObjectUtils.isEmpty(book.getStockSize()) && book.getStockSize().intValue() <=0){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_BOOK_STOCK_SIZE_WRONG_VALUE.getValue());
            }
            if(!ObjectUtils.isEmpty(book.getPrice()) && book.getPrice().intValue() <=0){
                ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_BOOK_PRICE_WRONG_VALUE.getValue());
            }
        }

        ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);
    }
}
