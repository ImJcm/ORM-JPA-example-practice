package com.imjcm.ormjpaexamplepractice.ex.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EX_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ex_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    // @Setter
    public void setName(String name) { this.name = name; }

    // @Setter
    public void setPrice(int price) { this.price = price; }

    // @Setter
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}
