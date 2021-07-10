package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository  extends CrudRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId, Pageable pageable);

    List<Order> findByOrderDateAfterAndOrderDateBefore(Date startDate, Date endDate);

}
