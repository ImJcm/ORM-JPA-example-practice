package com.imjcm.ormjpaexamplepractice.prac.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "food")
/*@TableGenerator(
        name = "FOOD_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "FOOD_SEQ",
        initialValue = 1,
        allocationSize = 1
)*/
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.TABLE,
        generator = "FOOD_SEQ_GENERATOR"
    )*/
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Builder
    public Food(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
