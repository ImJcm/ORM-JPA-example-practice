package com.imjcm.ormjpaexamplepractice.prac.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cart_food")
public class CartFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    /*@ManyToOne(
            optional = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
            //targetEntity = Entity.class (Generic Type)
    )*/
    @JoinColumn(name = "food_id")
    /*@JoinColumn(
            name = "food_id",
            referencedColumnName = "FOOD_ID",
            unique = false,
            nullable = true,
            insertable = true,
            updatable = true,
            columnDefinition = "varchar(30) default 'EMPTY'",
            table = "food"
    )*/
    private Food food;

    @Builder
    public CartFood(Cart cart, Food food) {
        this.cart = cart;
        this.food = food;
    }

    // @Setter
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // @Setter
    public void setFood(Food food) {
        this.food = food;
    }

}
