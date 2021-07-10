package com.getir.readingisgood.controller;

import com.getir.readingisgood.constant.APIConstants;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.service.BookService;
import com.getir.readingisgood.util.GetirResponse;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = APIConstants.API_URL + APIConstants.BOOK_URL)
public class BookController {

    private
    BookService bookService;

    @Autowired
    public void BookService(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public GetirResponse<List<Book>> findAll() {
        List<Book> bookDTOList = bookService.findAll();
        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_BOOK_LISTED.getValue()),
                bookDTOList
        );
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public GetirResponse<Book> saveOrUpdateBook(@RequestBody Book book) {
        bookService.saveOrUpdate(book);

        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_BOOK_SAVED.getValue()),
                book
        );
    }


}
