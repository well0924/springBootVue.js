package com.example.springbootvueproject.repository;

import com.example.springbootvueproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
