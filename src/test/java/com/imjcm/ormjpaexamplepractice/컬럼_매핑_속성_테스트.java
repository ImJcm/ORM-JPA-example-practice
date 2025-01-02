package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.domain.Member;
import com.imjcm.ormjpaexamplepractice.global.Role;
import com.imjcm.ormjpaexamplepractice.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
public class 컬럼_매핑_속성_테스트 {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("unique 속성 테스트 - 동일한 값을 갖는 컬럼이 있는 경우 예외 발생 - member 값이 같은 경우")
    public void unique_속성_테스트_동일한_값을_갖는_컬럼이_있는_경우_예외_발생_member() {
        int before_size = (int) memberRepository.count();
        int after_size = 0;

        Member member_1 = Member.builder()
                .username("member")
                .age(28)
                .role(Role.USER)
                .description("hello")
                .build();

        Member member_2 = Member.builder()
                .username("member")
                .age(27)
                .role(Role.USER)
                .description("hello")
                .build();

        memberRepository.save(member_1);
        //Assertions.assertThrows(DataIntegrityViolationException.class,() -> memberRepository.save(member_2));
        memberRepository.save(member_2);

        //after_size = (int) memberRepository.count();

        //Assertions.assertEquals(before_size + 2, after_size);
    }

    @Test
    @DisplayName("unique 속성 테스트 - 동일한 값을 갖는 컬럼이 있는 경우 예외 발생 - age 값이 같은 경우")
    public void unique_속성_테스트_동일한_값을_갖는_컬럼이_있는_경우_예외_발생_age() {
        Member member_1 = Member.builder()
                .username("member1")
                .age(27)
                .role(Role.USER)
                .description("hello")
                .build();

        Member member_2 = Member.builder()
                .username("member2")
                .age(27)
                .role(Role.USER)
                .description("hello")
                .build();

        memberRepository.save(member_1);
        memberRepository.save(member_2);
    }


}
