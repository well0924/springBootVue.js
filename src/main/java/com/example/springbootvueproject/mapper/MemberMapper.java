package com.example.springbootvueproject.mapper;

import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//1.componentModel은 map struct에 자동으로 주입
@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    //entity -> dto
    MemberResponse entityToDto(Member member);

    //dto -> entity
    //맵핑을 하는 과정에서 제외를 하고 싶은 컬럼은 ignore를 true
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdTime",ignore = true)
    @Mapping(target = "updatedTime",ignore = true)
    Member dtoToEntity(MemberRequest memberRequest);
}
