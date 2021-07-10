package com.getir.readingisgood.service;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
public class OrderServiceIntegrationTest {

    @Autowired
    OrderService orderService;

    @Test
    public void getORderByIdSizeTest(){
        Order o = orderService.getOrderById(1L);
        assertEquals(o.getBookId(),2);
    }

    @Test
    public void getORderByCustomerIdSizeTest(){
        List<Order> listORder = orderService.getOrderByCustomerId(1L,0,100);
        assertEquals(listORder.size(),8);
    }

    @Test
    public void getORderByDateIntervalSizeTest(){
        List<Order> listORder = orderService.getOrderByDateInterval(new Date(), new Date());
        assertEquals(listORder.size(),0);
    }

}
