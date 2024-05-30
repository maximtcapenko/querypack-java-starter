package com.querypack;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Configuration
	@Order(1)
	public static class RestSecurityConfiguration {

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.anonymous();
			
			return http.build();
		}

		protected static class ForbiddenAuthenticationEntryPoint implements
				AuthenticationEntryPoint {

			@Override
			public void commence(HttpServletRequest request,
					HttpServletResponse response,
					AuthenticationException authException) throws IOException,
					ServletException {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		}
	}
}
