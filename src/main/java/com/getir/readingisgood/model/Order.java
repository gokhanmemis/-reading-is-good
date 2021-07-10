package com.getir.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOK_ORDER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Column(name = "QUANTITY")
    private Long quantity;

}
