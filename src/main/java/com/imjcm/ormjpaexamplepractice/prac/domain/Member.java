package com.imjcm.ormjpaexamplepractice.prac.domain;

import com.imjcm.ormjpaexamplepractice.prac.global.Role;
import com.imjcm.ormjpaexamplepractice.prac.global.TimeStamped;
import jakarta.persistence.*;
import lombok.*;

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
//@Access(AccessType.FIELD)
public class Member extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.TABLE,
        generator = "MEMBER_SEQ_GENERATOR"
    )*/
    private long id;

    @Setter
    @Column(name = "username", nullable = false, length = 10)
    private String username;

    /*@Column(name = "nickname", nullable = false, unique = true, length = 11)
    private String nickname;

    @Column(name = "rank", precision = 1, scale = 1)
    private BigDecimal rank;*/

    @Setter
    @Column(name = "age", nullable = false)
    private int age;

    @Setter
    @Column(name = "role", nullable = false)
    //@Enumerated(value = EnumType.ORDINAL) //Role의 데이터가 순서인 값이 저장된다.
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Setter
    //@Temporal(value = TemporalType.TIMESTAMP) // Date + Time
    @Temporal(value = TemporalType.DATE)
    @Column(name = "birthday", updatable = false, nullable = false)
    private Date birthday;

    @Setter
    @Temporal(value = TemporalType.TIME)
    @Column(name = "birthtime")
    private Date birthtime;

    @Setter
    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "varchar(30) default 'EMPTY'")
    private String description;
    //private byte[] lobByte; - MySQL : lobByte Type

    @Transient
    private String tempStr = "Temp value";

    @Setter
    @OneToOne(mappedBy = "member") // 연관관계 주인을 설정 - Cart.member, Member - 주 테이블, Cart - 대상 테이블로 Cart가 Member에 종속된 관계를 부여
    private Cart cart;

    @Setter
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String username, int age, Role role, Date birthday, String description, List<Order> orders) {
        this.username = username;
        this.age = age;
        this.role = role;
        this.birthday = birthday;
        this.description = description;
        this.orders = orders;
    }

    /*@Access(AccessType.PROPERTY)
    public String getTempStr() {
        return tempStr;
    }*/
}
