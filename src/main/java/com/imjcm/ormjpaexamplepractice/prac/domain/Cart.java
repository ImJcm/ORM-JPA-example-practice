package com.imjcm.ormjpaexamplepractice.prac.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 연관관계의 주인 - 외래 키 관리
    @OneToOne
    @JoinColumn(name = "member_id", unique = true) // Member Entity's Id - Table Name_id(default) = Column Name
    private Member member;

    @Setter
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    // 연관관계 매핑 편의 메서드
    public void setMember(Member member) {
        if(this.member != null) {
            this.member.setCart(null);
        }

        this.member = member;
        this.member.setCart(this);
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setCart(this);
    }
}
