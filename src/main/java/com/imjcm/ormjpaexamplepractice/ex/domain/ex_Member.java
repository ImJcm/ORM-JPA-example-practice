package com.imjcm.ormjpaexamplepractice.ex.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EX_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ex_Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String address;

    @Builder
    public ex_Member(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // @Setter
    public void setName(String name) { this.name = name; }

    // @Setter
    public void setAddress(String address) { this.address = address; }

}
