package com.imjcm.ormjpaexamplepractice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age", nullable = false)
    private int age;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> foodList;

    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
        this.foodList = new ArrayList<>();
    }

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.setMember(this);
    }
}
