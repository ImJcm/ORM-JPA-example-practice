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

import java.util.Calendar;
import java.util.Date;

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

    @Test
    @DisplayName("uniqueConstraints 속성 테스트 - 동일한 값을 갖는 컬럼이 있는 경우 - username 값은 같지 않고, age 값이 같은 경우")
    public void uniqueConstraints_속성_테스트_동일한_값을_갖는_컬럼이_있는_경우_username_is_not_dupulicate_but_age_is_dupulicate() {
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

    @Test
    @DisplayName("uniqueConstraints 속성 테스트 - 동일한 값을 갖는 컬럼이 있는 경우 - username 값은 같고, age 값이 같지 않은 경우")
    public void uniqueConstraints_속성_테스트_동일한_값을_갖는_컬럼이_있는_경우_username_is_dupulicate_but_age_is_not_dupulicate() {
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
        memberRepository.save(member_2);
    }

    @Test
    @DisplayName("uniqueConstraints 속성 테스트 - 동일한 값을 갖는 컬럼이 있는 경우 - username, age 모두 같은 경우")
    public void uniqueConstraints_속성_테스트_동일한_값을_갖는_컬럼이_있는_경우_username_age_is_dupulicate() {
        Member member_1 = Member.builder()
                .username("member")
                .age(27)
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
        memberRepository.save(member_2);
    }

    @Test
    @DisplayName("TemporalType.DATE 속성 테스트 - 날짜 타입 컬럼 저장")
    public void temporalType_Date_속성_테스트_날짜_타입_컬럼_저장() {
        Member member = Member.builder()
                .username("member")
                .age(27)
                .role(Role.USER)
                .birthday(new Date(1997 - 1900, Calendar.SEPTEMBER,9))
                .description("hello")
                .build();

        Member findMember_from_persist = memberRepository.save(member);

        long member_id = findMember_from_persist.getId();

        Member findMember_from_database = memberRepository.findById(member_id).orElse(null);

        Assertions.assertEquals(findMember_from_database.getBirthday().getYear(),1997 - 1900);
        Assertions.assertEquals(findMember_from_database.getBirthday().getMonth(),Calendar.SEPTEMBER);
        Assertions.assertEquals(findMember_from_database.getBirthday().getDate(),9);
        // Date.getDay() : 요일 - sunday, monday, tuesday, wednesday, thursday, friday, saturday
    }

    @Test
    @DisplayName("TemporalType.TIME 속성 테스트 - 날짜 타입 컬럼 저장")
    public void temporalType_TIME_속성_테스트_날짜_타입_컬럼_저장() {
        Member member = Member.builder()
                .username("member")
                .age(27)
                .role(Role.USER)
                .birthday(new Date(1997 - 1900, Calendar.SEPTEMBER,9))
                .description("hello")
                .build();

        member.update_birthtime(new Date(1997 - 1900, Calendar.SEPTEMBER,9,1,35,19));

        Member findMember_from_persist = memberRepository.save(member);

        long member_id = findMember_from_persist.getId();

        Member findMember_from_database = memberRepository.findById(member_id).orElse(null);

        Assertions.assertEquals(findMember_from_database.getBirthtime().getHours(),1);
        Assertions.assertEquals(findMember_from_database.getBirthtime().getMinutes(),35);
        Assertions.assertEquals(findMember_from_database.getBirthtime().getSeconds(),19);
    }

    @Test
    @DisplayName("TemporalType.TIMESTAMP 속성 테스트 - 날짜 타입 컬럼 저장")
    public void temporalType_TIMESTAMP_속성_테스트_날짜_타입_컬럼_저장() {
        Date date = new Date(1997 - 1900, Calendar.SEPTEMBER, 9, 1, 35, 19);
        // Date 생성 시, Year, Month, Date, Hours, Minutes, Seconds를 지정하여 생성
    }


}
