package com.getir.readingisgood.service;

import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void CustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return (List<Customer>)customerRepository.findAll();
    }

    public void saveOrUpdate(Customer customer) {
        customerRepository.save(customer);
    }
}
