package com.getir.readingisgood.repository.dao;

import com.getir.readingisgood.repository.dao.dto.OrderDTO;
import com.getir.readingisgood.repository.dao.dto.StatisticDTO;
import com.getir.readingisgood.repository.dao.rowMapper.OrderRowMapper;
import com.getir.readingisgood.repository.dao.rowMapper.StatisticRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDAO {


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private void setDataSource(final DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<OrderDTO> getOrderAllData(){
        String sql = "SELECT OR.*,BO.BOOK_NAME, BO.AUTHOR, BO.ISBN, CU.FIRST_NAME, CU.LAST_NAME, CU.EMAIL " +
                "FROM BOOK_ORDER OR " +
                "INNER JOIN BOOK BO ON OR.BOOK_ID = BO.ID " +
                "INNER JOIN CUSTOMER CU ON OR.CUSTOMER_ID = CU.ID ";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new OrderRowMapper.OrderDTOMapper());
    }
}
