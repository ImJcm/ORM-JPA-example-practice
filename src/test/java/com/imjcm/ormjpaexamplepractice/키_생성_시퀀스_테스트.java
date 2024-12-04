package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.domain.Food;
import com.imjcm.ormjpaexamplepractice.domain.Member;
import com.imjcm.ormjpaexamplepractice.global.Role;
import com.imjcm.ormjpaexamplepractice.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class 키_생성_시퀀스_테스트 {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Table 생성 전략 테스트")
    public void table_생성_전략_테스트() {
        Member member = Member.builder()
                .age(28)
                .username("jcm")
                .role(Role.USER)
                .description("hello")
                .build();

        Food food = Food.builder()
                .price(1000L)
                .name("pizza")
                .build();

        Food food2 = Food.builder()
                .price(2000L)
                .name("chicken")
                .build();

        member.addFoodList(food);
        member.addFoodList(food2);

        memberRepository.save(member);

        Assertions.assertThat(member.getId()).isEqualTo(1);
        Assertions.assertThat(food.getId()).isEqualTo(1);
    }
}
