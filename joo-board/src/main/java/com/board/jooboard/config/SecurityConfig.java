package com.board.jooboard.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.board.jooboard.jwt.JWTFilter;
import com.board.jooboard.jwt.JWTUtil;
import com.board.jooboard.jwt.LoginFilter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;

/* 스프링 시큐리티의 인가 및 설정을 담당하는 클래스  */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
        private final AuthenticationConfiguration authenticationConfiguration;

        private final JWTUtil jwtUtil;

        public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,JWTUtil jwtUtil) {
    
            this.authenticationConfiguration = authenticationConfiguration;
            this.jwtUtil = jwtUtil;
        }

        // LoginFilter loginFilter = new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil)
        // loginFilter.setFilterProcessUrl("/api/login");
        


@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

      return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })));


        // csrf disable
        http.
                csrf((auth) -> auth.disable());

        // form 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());
                // .formLogin(login ->login
                // .loginPage("/user/login")
                // .defaultSuccessUrl("/api/list")
                // .permitAll());

        // http basic 인증 방식 disable
        http 
                .httpBasic((auth) -> auth.disable());

        // 경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/user/login", "/user/signup","/board/write","/resources/**","/status","/api/**","/board/write/**").permitAll() // 시큐리티 없이 들어갈수있는 곳 
                .requestMatchers("**").hasRole("USER")
                .anyRequest().authenticated());

        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);        
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil),UsernamePasswordAuthenticationFilter.class);

        // 세션 설정
        http
                .sessionManagement((session) -> session
                     .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // cors(cors -> cors.disable())
        //         .csrf(csrf -> csrf.disable())
        //         .authorizeHttpRequests(request -> request
        //         	.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
        //             .requestMatchers("user/login","/user/signup", "/resources/**","/api/signup","/status","api/login","api/dupliCheck").permitAll() // 시큐리티 없이 들어갈수있는 곳 
        //                 .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
        //         )
        //         .formLogin(login -> login	// form 방식 로그인 사용
        //             .loginPage("/user/login") // [A] 커스텀 로그인 페이지 지정
        //             // .loginProcessingUrl("/api/login") // [B] submit 받을 url
        //                 .usernameParameter("joocong")	// [C] submit할 아이디
        //                 .passwordParameter("1q2w3e4r!")    // [D] submit할 비밀번호
        //                 .defaultSuccessUrl("/api/list", true)	// 성공 시 dashboard로
        //                 .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
        //         );
                // .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

            
        return http.build();
    }



   

}