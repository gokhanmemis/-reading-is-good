package com.getir.readingisgood.service;

import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveOrUpdate(Book book) {
        bookRepository.save(book);
    }
}
