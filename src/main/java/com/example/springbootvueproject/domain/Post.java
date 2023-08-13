package com.example.springbootvueproject.domain;

import com.example.springbootvueproject.config.Base.BaseTime;
import com.example.springbootvueproject.domain.dto.request.PostRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Post extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String author;
    
    @Builder
    public Post(Long id,String title,String contents,String author){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    public void postUpdate(PostRequest post){
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }

}
