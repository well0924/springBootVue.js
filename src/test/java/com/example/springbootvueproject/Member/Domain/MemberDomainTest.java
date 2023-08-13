package com.example.springbootvueproject.Member.Domain;

import com.example.springbootvueproject.Factory.MemberFactory;
import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.mapper.MemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberDomainTest {

    @Test
    @DisplayName("Map Struct Entity->Dto")
    public void EntityToDto(){
        Member member = MemberFactory.member();

        MemberResponse response = MemberMapper.INSTANCE.entityToDto(member);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(member.getId());
        assertThat(response.getUserName()).isEqualTo(member.getUserName());
        assertThat(response.getPassword()).isEqualTo(member.getPassword());
        assertThat(response.getUserEmail()).isEqualTo(member.getUserEmail());
        assertThat(response.getUserAge()).isEqualTo(member.getUserAge());
        assertThat(response.getCreatedTime()).isEqualTo(member.getCreatedTime());
        assertThat(response.getUpdatedTime()).isEqualTo(member.getUpdatedTime());
    }

    @Test
    @DisplayName("Map Struct Dto->Entity")
    public void DtoToEntity(){
        MemberRequest memberRequest = MemberFactory.request();
        Member member = MemberMapper.INSTANCE.dtoToEntity(memberRequest);

        assertThat(member).isNotNull();
        assertThat(member.getUserName()).isEqualTo(memberRequest.getUserName());
        assertThat(memberRequest.getPassword()).isEqualTo(member.getPassword());
        assertThat(memberRequest.getUserAge()).isEqualTo(member.getUserAge());

    }
}
