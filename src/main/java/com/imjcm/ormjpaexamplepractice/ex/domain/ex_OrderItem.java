package com.imjcm.ormjpaexamplepractice.ex.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ex_order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ex_OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ex_Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ex_Order order;

    private int orderPrice;
    private int count;

    @Builder
    public ex_OrderItem(ex_Item item, ex_Order order, int orderPrice, int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    // @Setter
    public void setOrderPrice(int orderPrice) { this.orderPrice = orderPrice; }

    // @Setter
    public void setCount(int count) { this.count = count; }

    // @Setter
    public void setItem(ex_Item item) { this.item = item; }

    // @Setter
    public void setOrder(ex_Order order) { this.order = order; }
}
