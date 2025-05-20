package com.imjcm.ormjpaexamplepractice.prac.domain;

import com.imjcm.ormjpaexamplepractice.ex.global.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(OrderItem_ComplexId.class)
public class OrderItem_Complex {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private OrderStatus orderStatus;

    private int OrderAmount;

    private Date OrderDate;
}
