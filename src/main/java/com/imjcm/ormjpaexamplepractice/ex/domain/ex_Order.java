package com.imjcm.ormjpaexamplepractice.ex.domain;

import com.imjcm.ormjpaexamplepractice.ex.global.OrderStatus;
import com.imjcm.ormjpaexamplepractice.prac.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ex_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ex_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private ex_Member member;

    @OneToMany(mappedBy = "order")
    private List<ex_OrderItem> orderItems = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public ex_Order(ex_Member member, Date orderDate, OrderStatus status) {
        this.member = member;
        this.orderDate = orderDate;
        this.status = status;
    }

    // @Setter - 연관관계 매핑 편의 메서드
    public void setMember(ex_Member member) {
        if(this.member != null) {
            this.member.getOrders().remove(this);
        }

        this.member = member;
        this.member.getOrders().add(this);
    }

    public void addOrderItem(ex_OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // @Setter
    public void setOrderItems(List<ex_OrderItem> orderItems) { this.orderItems = orderItems; }

    // @Setter
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    // @Setter
    public void setOrderStatus(OrderStatus status) { this.status = status; }

}
