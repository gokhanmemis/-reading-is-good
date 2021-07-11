package com.getir.readingisgood.service;

import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.validation.Validator;
import com.getir.readingisgood.validation.servicesvalidator.BookValidation;
import com.getir.readingisgood.validation.servicesvalidator.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public void BookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return (List<Book>)bookRepository.findAll();
    }

    @Transactional
    @Validator(methodValidator = BookValidation.class)
    public void saveOrUpdate(Book book) {
        bookRepository.save(book);
    }
}
