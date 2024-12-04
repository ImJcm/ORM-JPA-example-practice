package com.imjcm.ormjpaexamplepractice;

import com.imjcm.ormjpaexamplepractice.domain.Member;
import com.imjcm.ormjpaexamplepractice.global.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // timestamp 시간 자동 생성 적용
public class OrmJpaExamplePracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrmJpaExamplePracticeApplication.class, args);
    }
}
