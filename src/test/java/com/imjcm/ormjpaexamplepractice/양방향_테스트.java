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
import java.util.List;

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

        //member.applyCart(cart);
        //cart.setMember(member);

        memberRepository.save(member);
        //cartRepository.save(cart); // member.cart - @OneToOne.cascade.CascadeALL : 영속성 전이로 member save 시, cart 또한 save 한다.
    }

    /*
        member.applyCart() 내부 로직 변경해야 정상 작동
        public void applyCart(Cart cart) {
            this.cart = cart;
            //cart.setMember(this); <- 주석 처리 해야 일방적인 연관관계를 설정한다.
        }
     */
    @Test
    public void member_cart_양방향_연관관계_테스트_실패() {
        // given
        member.applyCart(cart); // member -> cart 연관관계 설정

        // when
        Member exist_member = cart.getMember();

        // then
        Assertions.assertNull(exist_member);
    }

    @Test
    public void member_cart_양방향_연관관계_테스트_성공() {
        // given
        member.applyCart(cart);
        //cart.setMember(member); // member.applyCart 내부의 로직이 member.setCart(cart) + cart.setMember(this) 이므로 생략 가능하다.

        // when
        Member exist_member = cart.getMember();

        // then
        Assertions.assertNotNull(exist_member);
    }

}
