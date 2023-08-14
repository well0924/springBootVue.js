package com.example.springbootvueproject.config.Security.filter;

import com.example.springbootvueproject.config.Constant.CustomEnum;
import com.example.springbootvueproject.config.Exception.custom.ApiException;
import com.example.springbootvueproject.config.Security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(request);

        log.info("토큰값:"+token);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth); // 정상 토큰이면 SecurityContext에 저장
                log.info("인증정보:"+auth);
                log.info("Security Context Holder에 저장 되었습니다.");
            }else {
                log.info("??????");
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            throw new ApiException(CustomEnum.INVALID_JWT);
        }

        filterChain.doFilter(request, response);
    }
}
