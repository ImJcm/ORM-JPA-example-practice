package com.imjcm.ormjpaexamplepractice.ex.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ex_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ex_Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "member")
    private List<ex_Order> orders = new ArrayList<>();

    @Builder
    public ex_Member(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // @Setter
    public void setName(String name) { this.name = name; }

    // @Setter
    public void setAddress(String address) { this.address = address; }

    // @Setter
    public void setOrders(List<ex_Order> orders) { this.orders = orders; }

}
