package com.example.springbootvueproject.Member.Service;

import com.example.springbootvueproject.Factory.MemberFactory;
import com.example.springbootvueproject.config.Constant.CustomEnum;
import com.example.springbootvueproject.config.Exception.custom.ApiException;
import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.mapper.MemberMapper;
import com.example.springbootvueproject.repository.MemberRepository;
import com.example.springbootvueproject.service.MemberService;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;
    @Mock
    private MemberRepository memberRepository;
    @Spy
    private MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);

    @Test
    @DisplayName("회원 목록-성공")
    public void memberListTest(){

        List<Member> memberList = new ArrayList<>();
        List<MemberResponse>list = new ArrayList<>();

        memberList.add(MemberFactory.member());
        list.add(MemberFactory.response());

        given(memberRepository.findAll()).willReturn(memberList);

        List<MemberResponse>result = memberService.memberResponseList();

        System.out.println(result);
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getUserName()).isEqualTo(MemberFactory.member().getUserName());
    }

    @Test
    @DisplayName("회원 목록-실패")
    public void memberListTestFail(){

        ApiException apiException = Assertions.assertThrows(ApiException.class,()->{
            List<MemberResponse>list = memberService.memberResponseList();
        });

        assertThat(apiException.getCustomEnum()).isEqualTo(CustomEnum.MEMBER_01);
    }

    @Test
    @DisplayName("회원 조회-성공")
    public void memberDetailTest(){

        Member member = MemberFactory.member();
        MemberResponse response = memberMapper.entityToDto(member);
        System.out.println(response);
        given(memberRepository.findById(MemberFactory.member().getId())).willReturn(Optional.of(MemberFactory.member()));
        given(memberMapper.entityToDto(member)).willReturn(response);

        response = memberService.memberDetail(member.getId());

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("회원 조회-실패")
    public void memberDetailTestFail(){

        ApiException apiException = Assertions.assertThrows(ApiException.class,()->{
            MemberResponse response = memberService.memberDetail(4L);
        });

        assertThat(apiException.getCustomEnum()).isEqualTo(CustomEnum.MEMBER_01);
    }

    @Test
    @DisplayName("회원 가입-성공")
    public void memberCreatedTest(){
        MemberRequest memberRequest = MemberFactory.request();
        Member member = memberMapper.dtoToEntity(memberRequest);
        System.out.println(member);

        given(memberRepository.save(memberMapper.dtoToEntity(memberRequest))).willReturn(member);
        given(memberMapper.dtoToEntity(memberRequest)).willReturn(member);

        when(memberService.memberCreate(memberRequest)).thenReturn(member);
        Member result=memberService.memberCreate(memberRequest);

        assertThat(result).isNotNull();
        assertThat(result.getUserName()).isEqualTo(memberRequest.getUserName());
        assertThat(result.getUserEmail()).isEqualTo(memberRequest.getUserEmail());
        assertThat(result.getUserAge()).isEqualTo(memberRequest.getUserAge());
        assertThat(result.getUserEmail()).isEqualTo(memberRequest.getUserEmail());
    }

    @Test
    @DisplayName("회원 수정-성공")
    public void memberUpdatedTest(){
        Optional<Member> member = Optional.ofNullable(MemberFactory.member());

        MemberRequest request = MemberRequest.builder()
                .userEmail("update email")
                .userName("update name")
                .userAge(22)
                .password("well4149!")
                .build();

        MemberResponse response = memberMapper.entityToDto(member.get());

        given(memberRepository.findById(member.get().getId())).willReturn(member);
        given(memberMapper.entityToDto(member.get())).willReturn(response);

        member = memberRepository.findById(member.get().getId());
        member.ifPresent(m->m.memberUpdate(request));

        System.out.println(member.get());
    }

    @Test
    @DisplayName("회원 삭제-성공")
    public void memberDeleteTest(){
        Member member = MemberFactory.member();
        given(memberRepository.findById(member.getId())).willReturn(Optional.of(member));

        long count = memberRepository.count();
        memberService.memberDelete(member.getId());

        assertThat(count).isEqualTo(0);
    }
}
