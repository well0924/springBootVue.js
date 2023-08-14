package com.example.springbootvueproject.repository;

import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.repository.custom.MemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberCustomRepository {
    Optional<Member>findByUserName(String username);
}
