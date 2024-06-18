package com.imjcm.ormjpaexamplepractice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String username;

    @Column(name = "age", nullable = false)
    private int age;

    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public static Member createMember(String username, int age) {
        return Member.builder()
                .username(username)
                .age(age)
                .build();
    }
}
