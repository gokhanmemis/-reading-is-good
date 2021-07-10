package com.getir.readingisgood.repository.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private String month;
    private Long totalOrderCount;
    private Long totalBookCount;
    private Double totalPurcahasedAmount;

}
