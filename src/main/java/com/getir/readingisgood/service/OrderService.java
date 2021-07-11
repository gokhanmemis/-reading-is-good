package com.getir.readingisgood.service;

import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.repository.OrderRepository;
import com.getir.readingisgood.repository.dao.OrderDAO;
import com.getir.readingisgood.repository.dao.dto.OrderDTO;
import com.getir.readingisgood.util.ExceptionUtil;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import com.getir.readingisgood.validation.servicesvalidator.OrderValidation;
import com.getir.readingisgood.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private BookRepository bookRepository;
    private CustomerRepository customerRepository;
    private OrderDAO orderDAO;

    @Autowired
    public void OrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void BookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void OrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Autowired
    public void CustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Order> findAll() {
        return (List<Order>)orderRepository.findAll();
    }

    @Transactional
    @Validator(methodValidator = OrderValidation.class)
    public void saveOrUpdate(Order order) {
        List<String> messageCodeEnums = new ArrayList<>();
        if(!ObjectUtils.isEmpty(order.getCustomerId()) && !customerRepository.findById(order.getCustomerId()).isPresent()){
            ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_CUSTOMER_WRONG_OBJECT.getValue());
        }
        if(!ObjectUtils.isEmpty(order.getBookId()) && !bookRepository.findById(order.getBookId()).isPresent()){
            ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_BOOK_WRONG_OBJECT.getValue());
        }

        Book book = bookRepository.findById(order.getBookId()).get();
        if(book.getStockSize().compareTo(order.getQuantity()) <1){
            ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_NOT_ENOUGH_BOOK.getValue());
        }
        ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);

        book.setStockSize(book.getStockSize() - order.getQuantity());
        bookRepository.save(book);
        orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        Optional orderObject =  orderRepository.findById(id);
        if(orderObject.isPresent()){
            return (Order)orderObject.get();
        }
        List<String> messageCodeEnums = new ArrayList<>();
        ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_EMPTY_OBJECT.getValue());
        ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);
        return null;
    }

    public List<Order> getOrderByCustomerId(Long id,int page,int  size) {
        Pageable paging = PageRequest.of(page, size);
        List<Order> orderObject =  orderRepository.findByCustomerId(id, paging);
        if(!orderObject.isEmpty()){
            return orderObject;
        }
        List<String> messageCodeEnums = new ArrayList<>();
        ExceptionUtil.addToMessageList(messageCodeEnums, MessageListEnum.WARNING_ORDER_EMPTY_OBJECT.getValue());
        ExceptionUtil.throwIfAny(messageCodeEnums, MessageCodeEnum.WARNING);
        return null;
    }

    public List<Order> getOrderByDateInterval(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateAfterAndOrderDateBefore(startDate,endDate);
    }
    public List<OrderDTO> getOrderWithAllData() {
        return orderDAO.getOrderAllData();
    }

    public void deleteOrder(Long orderID){
        orderRepository.deleteById(orderID);
    }
}
