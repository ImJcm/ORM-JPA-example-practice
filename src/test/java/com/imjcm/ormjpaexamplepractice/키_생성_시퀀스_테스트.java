package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.prac.domain.Item;
import com.imjcm.ormjpaexamplepractice.prac.domain.Member;
import com.imjcm.ormjpaexamplepractice.prac.global.Role;
import com.imjcm.ormjpaexamplepractice.prac.repository.ItemRepository;
import com.imjcm.ormjpaexamplepractice.prac.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class 키_생성_시퀀스_테스트 {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("Table 생성 전략 테스트")
    public void table_생성_전략_테스트() {
        Member member = Member.builder()
                .age(28)
                .username("jcm")
                .role(Role.USER)
                .description("hello")
                .build();

        Item item = Item.builder()
                .price(1000L)
                .name("pizza")
                .build();

        Item item2 = Item.builder()
                .price(2000L)
                .name("chicken")
                .build();

        memberRepository.save(member);
        itemRepository.save(item);
        itemRepository.save(item2);

        Assertions.assertThat(member.getId()).isEqualTo(1);
        Assertions.assertThat(item.getId()).isEqualTo(1);
    }
}
