package com.example.sbp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration			// 이 파일이 스프링 환경 설정 파일이라는 것을 의미
@EnableWebSecurity		// 모든 요청 URL이 시큐리티의 제어를 받도록 만드는 어노테이션
@EnableMethodSecurity(prePostEnabled = true)	// 5.30) @PreAuthorize 로그인 되었는지 판별해주는 어노테이션 default값 : false
public class SecurityConfig {
	
	@Bean				// SecurityFilterChain 생성하지 않았지만, 객체를 생성해준다.
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
//			요청을 허용할지 결정하는 설정
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//				모든 요청을 허용한다는 의미
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		.csrf((csrf) -> csrf
				.ignoringRequestMatchers(new AntPathRequestMatcher
						("/h2-console/**")))
		.headers((headers) -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		.formLogin((formLogin) -> formLogin
			.loginPage("/user/login")
            .passwordParameter("pw")			// default = Password
			.defaultSuccessUrl("/question/list"))
		.logout((logout) -> logout
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
			.logoutSuccessUrl("/question/list")	//로그아웃 성공시 이동할 url
			.invalidateHttpSession(true))		//로그아웃시 생성된 세션 삭제 활성화
		;
	return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}