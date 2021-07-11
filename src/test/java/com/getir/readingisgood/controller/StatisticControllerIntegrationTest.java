package com.getir.readingisgood.controller;

import com.getir.readingisgood.ReadingIsGoodApplication;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.util.GetirResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
public class StatisticControllerIntegrationTest {

    @Autowired
    OrderController orderController;

    @Autowired
    StatisticsController statisticsController;
   @Test
    void saveOrUpdateOrder() {
        Calendar cal = Calendar.getInstance();
        cal.set(2021,5,5);
        Order order = new Order();
        order.setCustomerId(1L);
        order.setBookId(1L);
        order.setOrderDate(cal.getTime());
        order.setQuantity(1L);
        GetirResponse<Order> order1 = orderController.saveOrUpdateOrder(order);
        assertNotNull(order1.getData().getId());
       orderController.deleteOrderById(order1.getData().getId());
    }

    @Test
    void findAll() {
        assertNotNull(statisticsController.getStatistics().getData());
    }



}
