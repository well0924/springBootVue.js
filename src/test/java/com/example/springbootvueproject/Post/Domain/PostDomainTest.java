package com.example.springbootvueproject.Post.Domain;

import com.example.springbootvueproject.Factory.PostFactory;
import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.dto.request.PostRequest;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import com.example.springbootvueproject.mapper.PostMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostDomainTest {

    @Test
    @DisplayName("Map Struct Entity->Dto")
    public void EntityToDto(){
        Post post = PostFactory.post();
        PostResponse response = PostMapper.INSTANCE.entityToDto(post);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(post.getId());
        assertThat(response.getAuthor()).isEqualTo(post.getAuthor());
        assertThat(response.getTitle()).isEqualTo(post.getTitle());
        assertThat(response.getContents()).isEqualTo(post.getContents());
        assertThat(response.getCreatedTime()).isEqualTo(post.getCreatedTime());
        assertThat(response.getUpdatedTime()).isEqualTo(post.getUpdatedTime());
    }

    @Test
    @DisplayName("Map Struct Dto->Entity")
    public void DtoToEntity(){
        PostRequest postRequest = PostFactory.postRequest();
        Post post = PostMapper.INSTANCE.dtoToEntity(postRequest);

        assertThat(post).isNotNull();
        assertThat(post.getAuthor()).isEqualTo(postRequest.getAuthor());
        assertThat(post.getContents()).isEqualTo(postRequest.getContents());
        assertThat(post.getTitle()).isEqualTo(postRequest.getTitle());
    }
}
