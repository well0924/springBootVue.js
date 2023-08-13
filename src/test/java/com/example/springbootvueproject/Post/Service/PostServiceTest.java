package com.example.springbootvueproject.Post.Service;

import com.example.springbootvueproject.Factory.PostFactory;
import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.dto.request.PostRequest;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import com.example.springbootvueproject.mapper.PostMapper;
import com.example.springbootvueproject.repository.PostRepository;
import com.example.springbootvueproject.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PostServiceTest {

    @InjectMocks
    private PostService postService;
    @Mock
    private PostRepository postRepository;
    @Spy
    private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Test
    @DisplayName("게시판 목록")
    public void postList(){
        Post post = PostFactory.post();
        List<Post>postList = new ArrayList<>();
        postList.add(post);
        List<PostResponse>postResponseList = new ArrayList<>();
        given(postRepository.findAll()).willReturn(postList);

        postResponseList = postService.postResponseList();

        assertThat(postResponseList).isNotNull();
        assertThat(postResponseList.get(0).getContents()).isEqualTo(post.getContents());
        assertThat(postResponseList.get(0).getAuthor()).isEqualTo(post.getAuthor());
        assertThat(postResponseList.get(0).getTitle()).isEqualTo(post.getTitle());
        assertThat(postResponseList.get(0).getId()).isEqualTo(post.getId());
    }

    @Test
    @DisplayName("게시판 조회")
    public void postDetail(){

        Post post = PostFactory.post();
        PostResponse postResponse = postMapper.entityToDto(post);

        given(postRepository.findById(post.getId())).willReturn(Optional.of(post));
        given(postMapper.entityToDto(post)).willReturn(postResponse);
        postResponse = postService.postDetail(post.getId());

        assertThat(postResponse).isNotNull();
        assertThat(postResponse.getId()).isEqualTo(post.getId());
        assertThat(postResponse.getAuthor()).isEqualTo(post.getAuthor());
        assertThat(postResponse.getContents()).isEqualTo(post.getContents());
        assertThat(postResponse.getTitle()).isEqualTo(post.getTitle());
        assertThat(postResponse.getCreatedTime()).isEqualTo(post.getCreatedTime());
        assertThat(postResponse.getUpdatedTime()).isEqualTo(post.getUpdatedTime());
    }

    @Test
    @DisplayName("게시판 작성")
    public void postCreate(){
        PostRequest postRequest = PostFactory.postRequest();
        Post post = postMapper.dtoToEntity(postRequest);

        given(postRepository.save(postMapper.dtoToEntity(postRequest))).willReturn(post);
        given(postMapper.dtoToEntity(postRequest)).willReturn(post);

        when(postService.postCreate(postRequest)).thenReturn(post);
        Post result = postService.postCreate(postRequest);

        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("게시판 수정")
    public void postUpdate(){
        Optional<Post>postOptional = Optional.ofNullable(PostFactory.post());

        PostRequest postRequest = PostRequest
                .builder()
                .title("update title")
                .contents("update contents")
                .author("update author")
                .build();

        given(postRepository.findById(postOptional.get().getId())).willReturn(postOptional);

        postOptional = postRepository.findById(postOptional.get().getId());
        postOptional.ifPresent(p->p.postUpdate(postRequest));

        assertThat(postOptional.get()).isNotNull();
        assertThat(postOptional.get().getTitle()).isEqualTo(postRequest.getTitle());
    }

    @Test
    @DisplayName("게시판 삭제")
    public void postDelete(){
        Post post = PostFactory.post();
        given(postRepository.findById(post.getId())).willReturn(Optional.of(post));
        long count = postRepository.count();

        postService.postDelete(post.getId());

        assertThat(count).isEqualTo(0);
    }
}
