package com.imjcm.ormjpaexamplepractice.prac.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "CART_ID")
    private long id;

    @Setter
    @OneToOne(mappedBy = "cart")
    private Member member;
}
