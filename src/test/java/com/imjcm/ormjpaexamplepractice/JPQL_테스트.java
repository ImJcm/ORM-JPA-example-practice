package com.imjcm.ormjpaexamplepractice;


import com.imjcm.ormjpaexamplepractice.prac.domain.*;
import com.imjcm.ormjpaexamplepractice.prac.global.Role;
import com.imjcm.ormjpaexamplepractice.prac.repository.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    void clear_db() {
        memberRepository.deleteAll();
        cartRepository.deleteAll();
        cartItemRepository.deleteAll();
        itemRepository.deleteAll();
        orderRepository.deleteAll();
        orderItemRepository.deleteAll();
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
                .orders(new ArrayList<>())
                .build();

        Member member2 = Member.builder()
                .username("CMJ")
                .age(29)
                .birthday(new Date(1997 - 1900, Calendar.SEPTEMBER,9))
                .description("cmj")
                .role(Role.USER)
                .orders(new ArrayList<>())
                .build();

        memberRepository.save(member);
        memberRepository.save(member2);

        Item item1 = Item.builder()
                .name("item1")
                .price(1000L)
                .build();

        Item item2 = Item.builder()
                .name("item2")
                .price(2000L)
                .build();

        itemRepository.save(item1);
        itemRepository.save(item2);

        Cart cart = new Cart();
        cart.setMember(member);

        Cart cart2 = new Cart();
        cart2.setMember(member2);

        cartRepository.save(cart);
        cartRepository.save(cart2);

        CartItem cartItem1 = CartItem.builder()
                .cart(cart)
                .item(item1)
                .build();

        CartItem cartItem2 = CartItem.builder()
                .cart(cart)
                .item(item2)
                .build();

        CartItem cartItem3 = CartItem.builder()
                .cart(cart2)
                .item(item2)
                .build();

        cartItemRepository.save(cartItem1);
        cartItemRepository.save(cartItem2);
        cartItemRepository.save(cartItem3);

        cart.addCartItem(cartItem1);
        cart.addCartItem(cartItem2);
        cart2.addCartItem(cartItem3);

        Order order = Order.builder()
                .orderItems(new ArrayList<>())
                .orderDate(new Date())
                .build();

        order.setMember(member);

        orderRepository.save(order);

        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .orderPrice(item1.getPrice())
                .item(item1)
                .count(1)
                .build();

        orderItemRepository.save(orderItem);

        order.addOrderItem(orderItem);

        /*entityManager.flush();
        entityManager.clear();
        entityManager.close();*/
    }

    @Test
    @DisplayName("EntityManager.createQuery() - 엔티티_조회_used_by_JPQL")
    public void 엔티티_조회_used_by_JPQL() {
        String jpql = "select ci from CartItem cf join cart c where c.member.username =: memberName";

        List<CartItem> resultList = entityManager.createQuery(jpql, CartItem.class)
                .setParameter("memberName", "JCM")
                .getResultList();

        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("EntityManager.createQuery() - 엔티티_조회2_user_by_JPQL")
    public void 엔티티_조회_user_by_JPQL2() {
        String jpql = "select oi from OrderItem oi join order o where o.member.username =: memberName";
        // jpql - 데이터베이스 테이블 기준 x -> 객체 엔티티명 기준이므로 orders가 아닌 order이다.
        // 즉, 객체 엔티티를 기준으로 봐야한다.

        List<OrderItem> resultList = entityManager.createQuery(jpql, OrderItem.class)
                .setParameter("memberName", "JCM")
                .getResultList();

        assertThat(resultList.size()).isEqualTo(1);
    }
}
