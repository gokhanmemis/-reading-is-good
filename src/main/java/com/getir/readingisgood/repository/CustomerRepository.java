package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> getCustomerByEmail(String email);

}
