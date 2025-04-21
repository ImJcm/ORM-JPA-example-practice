package com.imjcm.ormjpaexamplepractice.prac.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "item")
/*@TableGenerator(
        name = "ITEM_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "ITEM_SEQ",
        initialValue = 1,
        allocationSize = 1
)*/
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.TABLE,
        generator = "ITEM_SEQ_GENERATOR"
    )*/
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "stockquantity")
    private int stockquantity;

    @Builder
    public Item(String name, Long price, int stockquantity) {
        this.name = name;
        this.price = price;
        this.stockquantity = stockquantity;
    }
}
