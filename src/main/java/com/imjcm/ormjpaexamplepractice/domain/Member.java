package com.imjcm.ormjpaexamplepractice.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String username;

    @Column(name = "age", nullable = false)
    private int age;

}
