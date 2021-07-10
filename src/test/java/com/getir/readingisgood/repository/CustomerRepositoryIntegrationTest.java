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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
class CustomerRepositoryIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findAll() {
        CustomerService customerService = Mockito.mock(CustomerService.class);
        //when(customerService.findAll()).thenReturn(null);
        assertEquals(((List<Customer>)customerRepository.findAll()).size(),4);
    }

    @Test
    void saveOrUpdateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test");
        customer.setFirstName("test");
        customer.setLastName("test");
        customerRepository.save(customer);
        assertEquals(customer.getId(),5);
        //when(customerController.saveOrUpdateCustomer()).thenReturn(null);
    }
}