package com.imjcm.ormjpaexamplepractice;

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
