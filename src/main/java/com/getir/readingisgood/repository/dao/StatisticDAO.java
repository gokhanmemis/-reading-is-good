package com.getir.readingisgood.repository.dao;

import com.getir.readingisgood.repository.dao.dto.StatisticDTO;
import com.getir.readingisgood.repository.dao.rowMapper.StatisticRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StatisticDAO {


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private void setDataSource(final DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<StatisticDTO> getOrderStatistic(){
        String sql = "SELECT CONCAT_WS(' ', MONTHNAME(order_date), YEAR(order_date)) as month,\n" +
                "       count(1)                                                as total_order_count,\n" +
                "       sum(quantity)                                           as total_book_count,\n" +
                "       ROUND(sum(quantity * b.price), 2)                       as total_purcahased_amount\n" +
                "FROM BOOK_order bo\n" +
                "         inner join book b on bo.book_id = b.id\n" +
                "group by CONCAT_WS(' ', MONTHNAME(order_date), YEAR(order_date))\n" +
                "order by month(order_date)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new StatisticRowMapper.StatisticDTOMapper());
    }
}
