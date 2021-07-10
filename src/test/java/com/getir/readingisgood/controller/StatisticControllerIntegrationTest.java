package com.getir.readingisgood.controller;

import com.getir.readingisgood.ReadingIsGoodApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadingIsGoodApplication.class)
public class StatisticControllerIntegrationTest {


    @Autowired
    StatisticsController statisticsController;

    @Test
    void findAll() {
        assertEquals(statisticsController.getStatistics().getData().size(),7);
    }

}
