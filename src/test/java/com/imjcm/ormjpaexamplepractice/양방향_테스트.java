package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.prac.domain.Cart;
import com.imjcm.ormjpaexamplepractice.prac.domain.Member;
import com.imjcm.ormjpaexamplepractice.prac.global.Role;
import com.imjcm.ormjpaexamplepractice.prac.repository.CartRepository;
import com.imjcm.ormjpaexamplepractice.prac.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class 양방향_테스트 {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CartRepository cartRepository;

    private Member member;
    private Cart cart;

    void clear_db() {
        memberRepository.deleteAll();
    }

    @BeforeEach
    void init() {
        clear_db();

        member = Member.builder()
                .username("JCM")
                .age(29)
                .birthday(new Date(1997 - 1900, Calendar.SEPTEMBER,9))
                .description("jcm")
                .role(Role.USER)
                .build();

        cart = new Cart();

        //cart.setMember(member);

        memberRepository.save(member);
        cartRepository.save(cart);  // Member_id의 외래 키 주인인 Cart에서 cascade를 통해 영속성 전이를 설정하면 memberRepository.save() 생략 가능
    }

    @Test
    public void member_cart_양방향_연관관계_테스트_실패() {
        // given
        member.setCart(cart); // member -> cart 연관관계 설정

        // when
        Member exist_member = cart.getMember();

        // then
        Assertions.assertNull(exist_member);
    }

    @Test
    public void member_cart_양방향_연관관계_테스트_성공() {
        // given
        cart.setMember(member); // member <-> cart 양방향 연관관계 매핑 편의 메서드를 통해 매핑

        // when
        Member exist_member = cart.getMember();

        // then
        Assertions.assertNotNull(exist_member);
    }

}
