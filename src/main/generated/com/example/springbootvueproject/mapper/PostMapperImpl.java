package com.example.springbootvueproject.mapper;

import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.Post.PostBuilder;
import com.example.springbootvueproject.domain.dto.request.PostRequest;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import com.example.springbootvueproject.domain.dto.response.PostResponse.PostResponseBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T07:53:24+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostResponse entityToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponseBuilder postResponse = PostResponse.builder();

        postResponse.id( post.getId() );
        postResponse.title( post.getTitle() );
        postResponse.contents( post.getContents() );
        postResponse.author( post.getAuthor() );
        postResponse.createdTime( post.getCreatedTime() );
        postResponse.updatedTime( post.getUpdatedTime() );

        return postResponse.build();
    }

    @Override
    public Post dtoToEntity(PostRequest postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        post.title( postRequest.getTitle() );
        post.contents( postRequest.getContents() );
        post.author( postRequest.getAuthor() );

        return post.build();
    }
}
