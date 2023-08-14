package com.example.springbootvueproject.service;

import com.example.springbootvueproject.config.Exception.custom.ApiException;
import com.example.springbootvueproject.config.Constant.CustomEnum;
import com.example.springbootvueproject.config.Security.jwt.JwtTokenProvider;
import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.Role;
import com.example.springbootvueproject.domain.dto.request.LoginDto;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.domain.dto.response.TokenDto;
import com.example.springbootvueproject.domain.dto.response.TokenResponse;
import com.example.springbootvueproject.mapper.MemberMapperImpl;
import com.example.springbootvueproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapperImpl memberMapper;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

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
                                member.getRole(),
                                member.getCreatedTime(),
                                member.getUpdatedTime()))
                .collect(Collectors.toList());
    }

    //회원 조회
    @Transactional
    public MemberResponse memberDetail(Long id){;
        return memberRepository.findById(id)
                .map(memberMapper::entityToDto)
                .orElseThrow(()->new ApiException(CustomEnum.MEMBER_01));
    }

    //회원 작성
    public Member memberCreate(MemberRequest memberRequest){

        memberRequest.setPassword(encoder.encode(memberRequest.getPassword()));
        memberRequest.setUserName(memberRequest.getUserName());
        memberRequest.setUserEmail(memberRequest.getUserEmail());
        memberRequest.setUserAge(memberRequest.getUserAge());
        memberRequest.setRole(Role.ROLE_USER);

        Member member = memberRepository.save(memberMapper.dtoToEntity(memberRequest));
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
        Optional<Member> member = Optional.of(memberRepository.findById(id).orElseThrow(()->new ApiException(CustomEnum.MEMBER_01)));
        memberRepository.deleteById(id);
    }

    //jwt 로그인
    @Transactional
    public TokenResponse signin(LoginDto loginDto,HttpServletResponse response){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUserName(),
                            loginDto.getPassword()
                    )
            );

            TokenResponse tokenDto = TokenResponse.builder()
                    .accessToken(jwtTokenProvider.createAccessToken(authentication))
                    .refreshToken(jwtTokenProvider.createRefreshToken(authentication))
                    .userDetail(authentication.getPrincipal().toString())
                    .build();

            //헤더 값 설정
            setHeader(response,tokenDto);

            return tokenDto;
        }catch(BadCredentialsException e){
            log.error("유효하지 않는 아이디와 패스워드입니다.");
            throw new ApiException(CustomEnum.SECURITY_02);
        }
    }

    //헤더 값 설정
    public void setHeader(HttpServletResponse response,TokenResponse tokenDto){
        response.addHeader("Authorization",tokenDto.getAccessToken());
        response.addHeader("Authorization",tokenDto.getRefreshToken());
    }
}
