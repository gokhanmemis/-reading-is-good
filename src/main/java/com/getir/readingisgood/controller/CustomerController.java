package com.getir.readingisgood.controller;

import com.getir.readingisgood.constant.APIConstants;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.util.GetirResponse;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = APIConstants.API_URL + APIConstants.CUSTOMER_URL)
public class CustomerController {

    private
    CustomerService customerService;

    private
    OrderService orderService;

    @Autowired
    public void CustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void OrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public GetirResponse<List<Customer>> findAll() {
        List<Customer> customerDTOList = customerService.findAll();
        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_CUSTOMER_LISTED.getValue()),
                customerDTOList
        );
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public GetirResponse<Customer> saveOrUpdateCustomer(@RequestBody Customer customer) {
        customerService.saveOrUpdate(customer);

        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_CUSTOMER_SAVED.getValue()),
                customer
        );
    }
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public GetirResponse<List<Order>> getOrderByCustomerId(@PathVariable("id") Long id,@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "3") int size) {
        List<Order> dto = orderService.getOrderByCustomerId(id, page, size);
        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_ORDER_GET_BY_ID.getValue()),
                dto
        );
    }

}
