package com.imjcm.ormjpaexamplepractice.domain;

import com.imjcm.ormjpaexamplepractice.global.Role;
import com.imjcm.ormjpaexamplepractice.global.TimeStamped;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    /*@Column(name = "nickname", nullable = false, unique = true, length = 11)
    private String nickname;

    @Column(name = "rank", precision = 1, scale = 1)
    private BigDecimal rank;*/
    
    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "role", nullable = false)
    //@Enumerated(value = EnumType.ORDINAL) //Role의 데이터가 순서인 값이 저장된다.
    @Enumerated(value = EnumType.STRING)
    private Role role;

    //@Temporal(value = TemporalType.TIMESTAMP) // Date + Time
    @Temporal(value = TemporalType.DATE)
    @Column(name = "birthday", updatable = false, nullable = false)
    private Date birthday;

    @Temporal(value = TemporalType.TIME)
    @Column(name = "birthtime")
    private Date birthtime;

    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "varchar(30) default 'EMPTY'")
    private String description;
    //private byte[] lobByte; - MySQL : lobByte Type

    @Builder
    public Member(String username, int age, Role role, Date birthday, String description) {
        this.username = username;
        this.age = age;
        this.role = role;
        this.birthday = birthday;
        this.description = description;
    }

    public void update_birthtime(Date time) {
        this.birthtime = time;
    }

    public void update(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Food> foodList = new ArrayList<>();

    /*@Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }*/

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.setMember(this);
    }
}
