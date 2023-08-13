package com.example.springbootvueproject.Post.Repository;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.config.JpaConfig;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import com.example.springbootvueproject.repository.PostRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    public void memberListTest(){
        Pageable pageRequest = PageRequest.of(0,5, Sort.by("id").descending());
        Page<PostResponse> postResponses = postRepository.postList(pageRequest);
        assertThat(postResponses).isNotNull();
        System.out.println(postResponses.toList());
    }

    @Test
    @DisplayName("게시글 검색 테스트")
    public void memberSearchTest(){
        String keyword ="test contents";
        Pageable pageRequest = PageRequest.of(0,5, Sort.by("id").descending());
        Page<PostResponse> postResponses = postRepository.findSearchAll(SearchType.c,keyword,pageRequest);
        assertThat(postResponses).isNotNull();
        assertThat(postResponses.toList().get(0).getContents()).isEqualTo(keyword);
    }
}
