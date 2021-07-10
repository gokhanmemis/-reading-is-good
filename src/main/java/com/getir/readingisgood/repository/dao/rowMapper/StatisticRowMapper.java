package com.getir.readingisgood.repository.dao.rowMapper;

import com.getir.readingisgood.repository.dao.dto.StatisticDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticRowMapper {

    public static class StatisticDTOMapper implements RowMapper<StatisticDTO> {

        public StatisticDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            StatisticDTO statisticDTO = new StatisticDTO();
            statisticDTO.setMonth(resultSet.getString("month"));
            statisticDTO.setTotalBookCount(resultSet.getLong("total_book_count"));
            statisticDTO.setTotalOrderCount(resultSet.getLong("total_order_count"));
            statisticDTO.setTotalPurcahasedAmount(resultSet.getDouble("total_purcahased_amount"));
            return statisticDTO;
        }
    }
}
