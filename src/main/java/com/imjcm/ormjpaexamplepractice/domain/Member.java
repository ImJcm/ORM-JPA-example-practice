package com.imjcm.ormjpaexamplepractice.domain;

import com.imjcm.ormjpaexamplepractice.global.Role;
import com.imjcm.ormjpaexamplepractice.global.TimeStamped;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
/*@Table(name = "member", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"username","age"}
)})*/
/*@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ",
        initialValue = 1,   // default = 0
        allocationSize = 1
)*/
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.TABLE,
        generator = "MEMBER_SEQ_GENERATOR"
    )*/
    private long id;

    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @Column(name = "nickname", nullable = false, unique = true, length = 11)
    private String nickname;

    @Column(name = "rank", precision = 1, scale = 1)
    private BigDecimal rank;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "varchar(30) default 'EMPTY'")
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

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();

    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.setMember(this);
    }
}
