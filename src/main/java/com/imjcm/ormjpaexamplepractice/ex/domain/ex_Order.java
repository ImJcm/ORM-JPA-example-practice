package com.imjcm.ormjpaexamplepractice.ex.domain;

import com.imjcm.ormjpaexamplepractice.ex.global.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ex_Order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ex_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public ex_Order(Long memberId, Date orderDate, OrderStatus status) {
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.status = status;
    }

    // @Setter
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    // @Setter
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    // @Setter
    public void setOrderStatus(OrderStatus status) { this.status = status; }

}
