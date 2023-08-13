package com.example.springbootvueproject.Factory;

import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.dto.request.PostRequest;
import com.example.springbootvueproject.domain.dto.response.PostResponse;

public class PostFactory {
    public static Post post(){
        return Post
                .builder()
                .id(1L)
                .author("author")
                .title("test title")
                .contents("test")
                .build();
    }

    public static PostRequest postRequest(){
        return PostRequest
                .builder()
                .author("author")
                .title("test title")
                .contents("oooo")
                .build();
    }

    public static PostResponse postResponse(){
        return PostResponse
                .builder()
                .id(1L)
                .author("author")
                .title("test title")
                .contents("test")
                .build();
    }
}
