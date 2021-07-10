package com.getir.readingisgood.service;

import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.repository.BookRepository;
import com.getir.readingisgood.repository.dao.StatisticDAO;
import com.getir.readingisgood.repository.dao.dto.StatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    StatisticDAO statisticDAO;

    @Autowired
    public void StatisticDAO(StatisticDAO statisticDAO) {
        this.statisticDAO = statisticDAO;
    }

    public List<StatisticDTO> getStatistics() {
        return statisticDAO.getOrderStatistic();
    }
}
