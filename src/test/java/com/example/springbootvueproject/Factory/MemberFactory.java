package com.example.springbootvueproject.Factory;

import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.Role;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class MemberFactory {
    public static Member member(){
        return Member
                .builder()
                .id(1L)
                .userName("test")
                .userEmail("test@Email.com")
                .password("qwefvrf112")
                .userAge(20)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
    }

    public static MemberRequest request(){
        return MemberRequest
                .builder()
                .password("qwefvrf112")
                .userAge(20)
                .userName("tester1")
                .userEmail("test1@Email.com")
                .role(Role.ROLE_USER)
                .build();
    }

    public static MemberResponse response(){
        return MemberResponse
                .builder()
                .id(1L)
                .userName("test")
                .userEmail("test@Email.com")
                .password("qwefvrf112")
                .userAge(20)
                .createdTime(LocalDateTime.now())
                .updatedTime(LocalDateTime.now())
                .build();
    }
}
