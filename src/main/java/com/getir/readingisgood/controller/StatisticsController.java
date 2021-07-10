package com.getir.readingisgood.controller;

import com.getir.readingisgood.constant.APIConstants;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.repository.dao.dto.StatisticDTO;
import com.getir.readingisgood.service.OrderService;
import com.getir.readingisgood.service.StatisticService;
import com.getir.readingisgood.util.GetirResponse;
import com.getir.readingisgood.util.MessageCodeEnum;
import com.getir.readingisgood.util.MessageListEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = APIConstants.API_URL + APIConstants.STATISTIC_URL)
public class StatisticsController {

    private
    StatisticService statisticService;

    @Autowired
    public void StatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public GetirResponse<List<StatisticDTO>> getStatistics() {
        List<StatisticDTO> orderDTOList = statisticService.getStatistics();
        return new GetirResponse<>(
                HttpStatus.OK,
                MessageCodeEnum.INFO,
                Arrays.asList(MessageListEnum.INFO_STATISTIC_LISTED.getValue()),
                orderDTOList
        );
    }


}
