package com.imjcm.ormjpaexamplepractice.prac.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Setter
    @ManyToOne
    /*@ManyToOne(
            optional = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
            //targetEntity = Entity.class (Generic Type)
    )*/
    @JoinColumn(name = "item_id")
    /*@JoinColumn(
            name = "item_id",
            referencedColumnName = "ITEM_ID",
            unique = false,
            nullable = true,
            insertable = true,
            updatable = true,
            columnDefinition = "varchar(30) default 'EMPTY'",
            table = "item"
    )*/
    private Item item;

    @Builder
    public CartItem(Cart cart, Item item) {
        this.cart = cart;
        this.item = item;
    }
}
