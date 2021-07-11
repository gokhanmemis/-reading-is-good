package com.getir.readingisgood.service;

import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.util.ExceptionUtil;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import com.getir.readingisgood.validation.Validator;
import com.getir.readingisgood.validation.servicesvalidator.CustomerValidation;
import com.getir.readingisgood.validation.servicesvalidator.OrderValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    @Validator(methodValidator = CustomerValidation.class)
    public void saveOrUpdate(Customer customer) {
        List<Customer> listCustomer = customerRepository.getCustomerByEmail(customer.getEmail());
        List<String> messageCodeEnums = new ArrayList<>();
        if(!listCustomer.isEmpty()){
            ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_CUSTOMER_EMAIL_SAME.getValue());
        }
        ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);
        customerRepository.save(customer);
    }
}
