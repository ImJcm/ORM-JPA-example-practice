package com.imjcm.ormjpaexamplepractice.ex.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EX_ORDER_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ex_OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_id")
    private Long orderId;

    private int orderPrice;
    private int count;

    // @Setter
    public void setOrderPrice(int orderPrice) { this.orderPrice = orderPrice; }

    // @Setter
    public void setCount(int count) { this.count = count; }

}
