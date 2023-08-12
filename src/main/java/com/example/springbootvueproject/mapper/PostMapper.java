package com.example.springbootvueproject.mapper;

import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.dto.request.PostRequest;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    //entity -> dto
    PostResponse entityToDto(Post post);
    //dto -> entity
    @Mapping(target = "id",ignore = true)
    Post dtoToEntity(PostRequest postRequest);

}
