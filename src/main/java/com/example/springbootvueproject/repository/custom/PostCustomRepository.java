package com.example.springbootvueproject.repository.custom;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    Page<PostResponse>postList(Pageable pageable);
    Page<PostResponse>findSearchAll(SearchType searchType, String keyword, Pageable pageable);
}
