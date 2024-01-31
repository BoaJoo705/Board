package com.board.jooboard.jwt;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.board.jooboard.dto.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginFilter extends UsernamePasswordAuthenticationFilter{
    
    // *** 이부분 나중에 다시보기 *** //
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;


    public LoginFilter(AuthenticationManager authenticationManager,JWTUtil jwtUtil) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        System.out.println("LoginFilter 클래스의  attemptAuthentication 접근");
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        System.out.println("username: "+username);
        System.out.println("password: "+password);

        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password,null);
        
        return authenticationManager.authenticate(authToken); // 자동으로 authenticationManager에서 검증
    }


    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();
        System.out.println("role:"+role);

        String token = jwtUtil.createJwt(username, role, 60*60*10L); // 토큰 살아있는 시간 
        System.out.println("token:"+token);

        response.addHeader("Authorization", "Bearer " + token); // 인증방식 띄어쓰기 있어야함 ->http 인증방식 RFC 7235 정의에 따라 헤더형태 정해줘야하기 때문


        // to do 
        String beartoken = "Bearer " + token;
        HttpSession session = request.getSession();
        // 세션에 저장된 값 가져오기
        session.setAttribute("jwtToken",beartoken);
        System.out.println("jwtToken 세션 저장 완료");
        // to do 


        System.out.println("successfulAuthentication success!!!!!!!!!");
        
    }

		//로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("successfulAuthentication fail!!!!!!!!!");
        response.setStatus(401);
    }


}
