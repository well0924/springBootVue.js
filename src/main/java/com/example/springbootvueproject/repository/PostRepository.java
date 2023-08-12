package com.example.springbootvueproject.repository;

import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.repository.custom.PostCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long>, PostCustomRepository {

}
