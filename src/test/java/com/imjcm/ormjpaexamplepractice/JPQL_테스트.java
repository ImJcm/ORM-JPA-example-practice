package com.imjcm.ormjpaexamplepractice;


import com.imjcm.ormjpaexamplepractice.prac.domain.Cart;
import com.imjcm.ormjpaexamplepractice.prac.domain.CartFood;
import com.imjcm.ormjpaexamplepractice.prac.domain.Food;
import com.imjcm.ormjpaexamplepractice.prac.domain.Member;
import com.imjcm.ormjpaexamplepractice.prac.global.Role;
import com.imjcm.ormjpaexamplepractice.prac.repository.CartFoodRepository;
import com.imjcm.ormjpaexamplepractice.prac.repository.FoodRepository;
import com.imjcm.ormjpaexamplepractice.prac.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JPQL_테스트 {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CartFoodRepository cartFoodRepository;

    @Autowired
    private FoodRepository foodRepository;

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

        Member member2 = Member.builder()
                .username("CMJ")
                .age(29)
                .birthday(new Date(1997 - 1900, Calendar.SEPTEMBER,9))
                .description("cmj")
                .role(Role.USER)
                .build();

        Cart cart2 = new Cart();

        Food food1 = Food.builder()
                .name("food1")
                .price(1000L)
                .build();

        Food food2 = Food.builder()
                .name("food2")
                .price(2000L)
                .build();

        // builder 내부에서 setter 진행
        CartFood cartFood1 = CartFood.builder()
                .cart(cart)
                .food(food1)
                .build();

        // builder 내부에서 setter 진행
        CartFood cartFood2 = CartFood.builder()
                .cart(cart)
                .food(food2)
                .build();

        // builder 내부에서 setter 진행
        CartFood cartFood3 = CartFood.builder()
                .cart(cart2)
                .food(food2)
                .build();

        member.applyCart(cart);
        memberRepository.save(member);

        member2.applyCart(cart2);
        memberRepository.save(member2);

        foodRepository.save(food1);
        foodRepository.save(food2);

        cartFoodRepository.save(cartFood1);
        cartFoodRepository.save(cartFood2);
        cartFoodRepository.save(cartFood3);

        /*entityManager.flush();
        entityManager.clear();
        entityManager.close();*/
    }

    @Test
    @DisplayName("EntityManager.createQuery() - 엔티티_조회_used_by_JPQL")
    public void 엔티티_조회_used_by_JPQL() {
        String jpql = "select cf from CartFood cf join cf.cart c where c.member.username =: memberName";

        List<CartFood> resultList = entityManager.createQuery(jpql, CartFood.class)
                .setParameter("memberName", "jcm")
                .getResultList();

        assertThat(resultList.size()).isEqualTo(2);
    }
}
