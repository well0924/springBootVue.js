package com.example.springbootvueproject.Member.Repository;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.config.JpaConfig;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@DataJpaTest
@Import({JpaConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 목록 조회 테스트")
    public void memberListTest(){
        Pageable pageRequest = PageRequest.of(0,5, Sort.by("id").descending());
        Page<MemberResponse>memberResponses = memberRepository.memberList(pageRequest);
        System.out.println(memberResponses.stream().toList());
    }

    @Test
    @DisplayName("회원 검색 테스트")
    public void memberSearchTest(){
        String keyword ="tester2@naver.com";
        Pageable pageRequest = PageRequest.of(0,5, Sort.by("id").descending());
        Page<MemberResponse>memberResponses = memberRepository.memberSearch(SearchType.e,keyword,pageRequest);
        System.out.println(memberResponses.stream().toList());
    }
}
