package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.prac.domain.Cart;
import com.imjcm.ormjpaexamplepractice.prac.domain.CartFood;
import com.imjcm.ormjpaexamplepractice.prac.domain.Food;
import com.imjcm.ormjpaexamplepractice.prac.domain.Member;
import com.imjcm.ormjpaexamplepractice.prac.global.Role;
import com.imjcm.ormjpaexamplepractice.prac.repository.CartRepository;
import com.imjcm.ormjpaexamplepractice.prac.repository.FoodRepository;
import com.imjcm.ormjpaexamplepractice.prac.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class 지연_로딩_테스트_클래스 {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private EntityManager entityManager;


    void clear_db() {
        memberRepository.deleteAll();
    }

    @BeforeEach
    void init() {
        clear_db();

        Member member = Member.builder()
                .username("JCM")
                .age(29)
                .birthday(new Date(1997 - 1900, Calendar.SEPTEMBER,9))
                .description("jcm")
                .role(Role.USER)
                .build();

        Cart cart = new Cart();

        /*Food food1 = Food.builder()
                .name("food1")
                .price(1000L)
                .build();

        Food food2 = Food.builder()
                .name("food2")
                .price(2000L)
                .build();

        CartFood cartFood1 = CartFood.builder()
                .cart(cart)
                .food(food1)
                .build();

        CartFood cartFood2 = CartFood.builder()
                .cart(cart)
                .food(food2)
                .build();*/

        cart.setMember(member);

        memberRepository.save(member);

        /*entityManager.flush();
        entityManager.clear();
        entityManager.close();*/
    }

    @Test
    @DisplayName("연관관계 객체가 프록시 객체라면 성공")
    @Transactional
    public void 연관관계_객체가_프록시객체라면_성공() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member.getCart());

        Assertions.assertThat(isLoad).isEqualTo(false);
    }

    @Test
    @DisplayName("연관관계 객체가 초기화 되었다면 성공")
    @Transactional
    public void 연관관계_객체가_초기화_되었다면_성공() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        Member exist_member = member.getCart().getMember();

        boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member.getCart());

        Assertions.assertThat(isLoad).isEqualTo(true);
    }
}
