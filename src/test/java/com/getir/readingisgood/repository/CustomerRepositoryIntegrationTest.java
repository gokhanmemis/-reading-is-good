package com.getir.readingisgood.repository;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
class CustomerRepositoryIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findAll() {
        CustomerService customerService = Mockito.mock(CustomerService.class);
        assertNotNull((List<Customer>)customerRepository.findAll());
    }

    @Test
    void saveOrUpdateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test");
        customer.setFirstName("test");
        customer.setLastName("test");
        customerRepository.save(customer);
        assertNotNull(customer.getId());
    }
}