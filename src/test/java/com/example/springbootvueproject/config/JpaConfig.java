package com.example.springbootvueproject.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestConfiguration
public class JpaConfig {
    //EntityManager를 빈으로 주입할 때 사용하는 어노테이션
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
