package com.getir.readingisgood.controller;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.util.GetirResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
public class OrderControllerIntegrationTest {


    @Autowired
    OrderController orderController;

    @Test
    void findAll() {
        assertEquals(orderController.findAll().getData().size(),8);
    }

    @Test
    public void getORderByIdSizeTest(){
        GetirResponse<Order> o = orderController.getOrderById(1L);
        assertEquals(o.getData().getBookId(),2);
    }

    @Test
    public void getORderByDateIntervalSizeTest(){
        GetirResponse<List<Order>> listORder = orderController.getOrdersByDate(new Date(), new Date());
        assertEquals(listORder.getData().size(),0);
    }

}
