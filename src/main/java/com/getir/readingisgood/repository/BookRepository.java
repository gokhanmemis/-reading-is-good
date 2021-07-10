package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
