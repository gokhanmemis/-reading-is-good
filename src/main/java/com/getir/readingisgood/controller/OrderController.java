package com.getir.readingisgood.controller;

import com.getir.readingisgood.constant.APIConstants;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.util.GetirResponse;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = APIConstants.API_URL + APIConstants.ORDER_URL)
public class OrderController {

    private
    OrderService orderService;

    @Autowired
    public void OrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public GetirResponse<List<Order>> findAll() {
        List<Order> orderDTOList = orderService.findAll();
        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_ORDER_LISTED.getValue()),
                orderDTOList
        );
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public GetirResponse<Order> saveOrUpdateOrder(@RequestBody Order order) {
        orderService.saveOrUpdate(order);

        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_ORDER_SAVED.getValue()),
                order
        );
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GetirResponse<Order> getOrderById(@PathVariable("id") Long id) {
        Order dto = orderService.getOrderById(id);
        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_ORDER_GET_BY_ID.getValue()),
                dto
        );
    }

    @RequestMapping(value = "/getOrdersByDate", method = RequestMethod.POST)
    public GetirResponse<List<Order>> getOrdersByDate(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date endDate) {
        List<Order> dto = orderService.getOrderByDateInterval(startDate,endDate);

        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_ORDER_LISTED.getValue()),
                dto
        );
    }

}
