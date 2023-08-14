package com.example.springbootvueproject.config.Security.auth;

import com.example.springbootvueproject.config.Constant.CustomEnum;
import com.example.springbootvueproject.config.Exception.custom.ApiException;
import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member>member = Optional.ofNullable(memberRepository.findByUserName(username)
                .orElseThrow(() -> new ApiException(CustomEnum.MEMBER_01)));

        log.info("user detail service");
        log.info("회원 정보:"+member.get());

        return new CustomUserDetail(member.get());
    }
}
