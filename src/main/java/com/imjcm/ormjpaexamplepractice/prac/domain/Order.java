package com.imjcm.ormjpaexamplepractice.prac.domain;

import com.imjcm.ormjpaexamplepractice.ex.global.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Setter
    @Temporal(value = TemporalType.DATE)
    private Date orderDate;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Setter
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    /*
        @ManyToMany + @JoinTable를 사용하여 다대다 연관관계를 구성
        해당 방법을 사용하면 Order_Item 연결 테이블을 생성하여 다대다 관계를 구성한다.
        하지만 이 방법은 OrderStatus, OrderItem, ... 과 같은 컬럼을 추가할 수 없다.
     */
    @ManyToMany
    @JoinTable(name = "Order_Item",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<Item>();


    @Builder
    public Order(Member member, Date orderDate, OrderStatus orderStatus, List<OrderItem> orderItems) {
        this.member = member;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
    }

    // 연관관계 매핑 편의 메서드
    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getOrders().remove(this);
        }

        this.member = member;
        this.member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
}
