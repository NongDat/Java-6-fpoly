package com.example.j6demo5.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal price;
    Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order")
    Order order;
    @ManyToOne
    @JoinColumn(name = "product")
    Product product;
}
