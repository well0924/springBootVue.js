package com.example.springbootvueproject.service;

import com.example.springbootvueproject.config.Exception.custom.ApiException;
import com.example.springbootvueproject.config.Constant.CustomEnum;
import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.mapper.MemberMapperImpl;
import com.example.springbootvueproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapperImpl memberMapper;

    //회원 목록
    @Transactional
    public List<MemberResponse>memberResponseList(){
        List<Member>memberList = memberRepository.findAll();
        if(memberList.isEmpty()){
            throw new ApiException(CustomEnum.MEMBER_01);
        }
        return memberList.stream()
                .map(member ->new MemberResponse(
                                member.getId(),
                                member.getUserName(),
                                member.getPassword(),
                                member.getUserAge(),
                                member.getUserEmail(),
                                member.getCreatedTime(),
                                member.getUpdatedTime()))
                .collect(Collectors.toList());
    }

    //회원 조회
    @Transactional
    public MemberResponse memberDetail(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new ApiException(CustomEnum.MEMBER_01));

        MemberResponse memberResponse = memberMapper.entityToDto(member);

        return memberResponse;
    }

    //회원 작성
    public Member memberCreate(MemberRequest memberRequest){
        memberRequest.setUserName(memberRequest.getUserName());
        memberRequest.setUserEmail(memberRequest.getUserEmail());
        memberRequest.setUserAge(memberRequest.getUserAge());

        Member member = memberMapper.dtoToEntity(memberRequest);
        member = memberRepository.save(member);
        return member;
    }

    //회원 수정
    @Transactional
    public Member memberUpdate(Long id,MemberRequest memberRequest){
        Optional<Member>memberDetail = Optional.of(memberRepository.findById(id)
                .orElseThrow(()->new ApiException(CustomEnum.MEMBER_01)));

        memberDetail.ifPresent(member -> member.memberUpdate(memberRequest));

        return memberRepository.save(memberDetail.get());
    }

    //회원 삭제
    @Transactional
    public void memberDelete(Long id){
        Optional<Member> member = Optional.of(memberRepository.findById(id).orElseThrow());
        memberRepository.deleteById(id);
    }

}
