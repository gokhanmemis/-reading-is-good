package com.getir.readingisgood.service;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
public class BookServiceIntegrationTest {

    @Autowired
    BookService bookService;


    @Test
    void findAllAndSaveNew() {
        assertEquals(bookService.findAll().size(),6);

        Book book = new Book();
        book.setBookName("test");
        book.setAuthor("test");
        book.setIsbn("test");
        book.setPrice(2.99);
        book.setStockSize(5L);
        bookService.saveOrUpdate(book);
        assertEquals(book.getId(),7);
    }

    @Test
    void saveOrUpdateBook() {
        Book book = new Book();
        book.setBookName("test");
        book.setAuthor("test");
        book.setIsbn("test");
        book.setPrice(2.99);
        book.setStockSize(5L);
        //bookService.saveOrUpdate(book);
        assertEquals(book.getId(),null);
    }

}
