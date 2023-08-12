package com.example.springbootvueproject.service;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.config.Exception.custom.ApiException;
import com.example.springbootvueproject.config.Constant.CustomEnum;
import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.dto.request.PostRequest;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import com.example.springbootvueproject.mapper.PostMapperImpl;
import com.example.springbootvueproject.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapperImpl postMapper;

    //게시글 목록
    @Transactional(readOnly = true)
    public List<PostResponse>postResponseList(){
        List<Post>postList = postRepository.findAll();
        if(postList.isEmpty()){
            throw new ApiException(CustomEnum.POST_01);
        }
        return postList.stream()
                .map(post->new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getContents(),
                        post.getAuthor(),
                        post.getCreatedTime(),
                        post.getUpdatedTime()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PostResponse>postPagingResponse(Pageable pageable){
        Page<PostResponse>list = postRepository.postList(pageable);
        if(list.isEmpty()){
            throw new ApiException(CustomEnum.POST_01);
        }
        return list;
    }

    //게시글 단일조회
    @Transactional(readOnly = true)
    public PostResponse postDetail(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new ApiException(CustomEnum.POST_01));
        PostResponse response = postMapper.entityToDto(post);
        return response;
    }

    //게시물 검색
    @Transactional
    public Page<PostResponse>postSearch(SearchType searchType, String keyword, Pageable pageable){
        return postRepository.findSearchAll(searchType,keyword,pageable);
    }

    //게시글 작성
    public Post postCreate(PostRequest postRequest){
        postRequest.setTitle(postRequest.getTitle());
        postRequest.setContents(postRequest.getContents());
        postRequest.setAuthor(postRequest.getAuthor());

        Post post = postMapper.dtoToEntity(postRequest);

        return postRepository.save(post);
    }

    //게시글 수정
    public Post postUpdate(Long id,PostRequest postRequest){
        Optional<Post>detail = Optional.of(postRepository.findById(id).orElseThrow());
        detail.ifPresent(post -> post.postUpdate(postRequest));
        return postRepository.save(detail.get());
    }

    //게시글 삭제
    public void postDelete(Long id){
        Optional<Post>detail = Optional.of(postRepository.findById(id).orElseThrow());
        postRepository.deleteById(id);
    }
}
