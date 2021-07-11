package com.getir.readingisgood.repository.dao.rowMapper;

import com.getir.readingisgood.repository.dao.dto.OrderDTO;
import com.getir.readingisgood.repository.dao.dto.StatisticDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper {

    public static class OrderDTOMapper implements RowMapper<OrderDTO> {

        public OrderDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderDate(resultSet.getDate("order_date"));
            orderDTO.setQuantity(resultSet.getLong("quantity"));
            orderDTO.setAuthor(resultSet.getString("author"));
            orderDTO.setBookId(resultSet.getLong("book_id"));
            orderDTO.setBookName(resultSet.getString("book_name"));
            orderDTO.setIsbn(resultSet.getString("isbn"));
            orderDTO.setCustomerEmail(resultSet.getString("email"));
            orderDTO.setCustomerFirstName(resultSet.getString("first_name"));
            orderDTO.setCustomerLastName(resultSet.getString("last_name"));
            orderDTO.setCustomerId(resultSet.getLong("customer_id"));
            orderDTO.setId(resultSet.getLong("id"));
            return orderDTO;
        }
    }
}
