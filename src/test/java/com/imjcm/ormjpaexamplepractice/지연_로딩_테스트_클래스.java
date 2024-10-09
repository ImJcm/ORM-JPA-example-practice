package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.domain.Food;
import com.imjcm.ormjpaexamplepractice.domain.Member;
import com.imjcm.ormjpaexamplepractice.repository.FoodRepository;
import com.imjcm.ormjpaexamplepractice.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class 지연_로딩_테스트_클래스 {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private EntityManager entityManager;


    void clear_db() {
        memberRepository.deleteAll();
    }

    @BeforeEach
    @Rollback(value = false)
    void init() {
        clear_db();

        Member member = Member.builder()
                .username("JCM")
                .age(29)
                .build();

        Food food1 = Food.builder()
                .name("food1")
                .build();

        Food food2 = Food.builder()
                .name("food2")
                .build();

        member.addFoodList(food1);
        member.addFoodList(food2);

        memberRepository.save(member);

        entityManager.flush();
        entityManager.clear();
        entityManager.close();
    }

    @Test
    @DisplayName("연관관계 객체가 프록시 객체라면 성공")
    @Transactional
    public void 연관관계_객체가_프록시객체라면_성공() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member.getFoodList());

        Assertions.assertThat(isLoad).isEqualTo(false);
    }

    @Test
    @DisplayName("연관관계 객체가 초기화 되었다면 성공")
    @Transactional
    public void 연관관계_객체가_초기화_되었다면_성공() {
        Member member = memberRepository.findById(1L).orElseThrow(RuntimeException::new);

        int size = member.getFoodList().size();

        boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member.getFoodList());

        Assertions.assertThat(isLoad).isEqualTo(true);
    }
}
