package com.imjcm.ormjpaexamplepractice.domain;

import com.imjcm.ormjpaexamplepractice.global.Role;
import com.imjcm.ormjpaexamplepractice.global.TimeStamped;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"username","age"}
)})
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Builder
    public Member(String username, int age, Role role, String description) {
        this.username = username;
        this.age = age;
        this.role = role;
        this.description = description;
    }

    public void update(String description) {
        this.description = description;
    }

    /*@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> foodList;*/

    /*@Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
        this.foodList = new ArrayList<>();
    }

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.setMember(this);
    }*/
}
