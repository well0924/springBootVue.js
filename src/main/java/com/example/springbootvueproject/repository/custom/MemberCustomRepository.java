package com.example.springbootvueproject.repository.custom;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberCustomRepository {
    //회원 목록
    Page<MemberResponse>memberList(Pageable pageable);
    //회원 검색
    Page<MemberResponse>memberSearch(SearchType searchType, String keyword, Pageable pageable);
}
