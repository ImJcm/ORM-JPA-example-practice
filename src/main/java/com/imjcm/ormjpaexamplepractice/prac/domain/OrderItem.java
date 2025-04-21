package com.imjcm.ormjpaexamplepractice.prac.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Setter
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Setter
    private Long orderPrice;

    @Setter
    private int count;

    @Builder
    public OrderItem(Order order, Item item, Long orderPrice, int count) {
        this.order = order;
        this.item = item;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
