package com.getir.readingisgood.repository.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;

    private Long bookId;

    private Long customerId;

    private Date orderDate;

    private Long quantity;

    private String bookName;

    private String author;

    private String isbn;

    private String customerFirstName;

    private String customerLastName;

    private String customerEmail;

}
