package com.imjcm.ormjpaexamplepractice.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Food(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
