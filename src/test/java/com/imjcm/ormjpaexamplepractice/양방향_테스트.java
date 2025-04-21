package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.prac.domain.*;
import com.imjcm.ormjpaexamplepractice.prac.global.Role;
import com.imjcm.ormjpaexamplepractice.prac.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class 양방향_테스트 {
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

    private Member member;
    private Cart cart;
    private CartItem cartItem;
    private Order order;
    private OrderItem orderItem;
    private Item item;

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

        member = Member.builder()
                .username("JCM")
                .age(29)
                .birthday(new Date(1997 - 1900, Calendar.SEPTEMBER,9))
                .description("jcm")
                .role(Role.USER)
                .orders(new ArrayList<>())
                .build();

        memberRepository.save(member);

        cart = new Cart();

        cartRepository.save(cart);  // Member_id의 외래 키 주인인 Cart에서 cascade를 통해 영속성 전이를 설정하면 memberRepository.save() 생략 가능

        item = Item.builder()
                .name("item1")
                .price(10000L)
                .build();

        itemRepository.save(item);

        cartItem = CartItem.builder()
                .build();

        cartItemRepository.save(cartItem);

        order = Order.builder()
                .orderDate(new Date())
                .orderItems(new ArrayList<>())
                .build();

        order.setMember(member);

        orderRepository.save(order);

        orderItem = OrderItem.builder()
                .build();

        orderItemRepository.save(orderItem);
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

    @Test
    public void cart_cartItem_양방향_연관관계_테스트_성공() {
        // given
        cartItem.setItem(item);
        cartItem.setCart(cart);
        cart.addCartItem(cartItem);

        // when
        List<CartItem> cartItems = cart.getCartItems();

        // then
        Assertions.assertNotNull(cartItems);
    }

    @Test
    public void member_order_양방향_연관관계_테스트_성공() {
        // given
        order.setMember(member);

        // when
        Member exist_member = order.getMember();

        // then
        Assertions.assertNotNull(exist_member);
    }

    @Test
    public void order_orderItem_양방향_연관관계_테스트_성공() {
        // given
        orderItem.setItem(item);
        orderItem.setOrder(order);
        order.addOrderItem(orderItem);

        // when
        List<OrderItem> orderItems = order.getOrderItems();

        // then
        Assertions.assertNotNull(orderItems);
    }
}
