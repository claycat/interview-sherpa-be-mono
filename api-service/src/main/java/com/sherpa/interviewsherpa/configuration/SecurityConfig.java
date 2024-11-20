package com.sherpa.interviewsherpa.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.sherpa.interviewsherpa.auth.application.service.FlowAccessControlService;
import com.sherpa.interviewsherpa.auth.domain.FlowPermissionEvaluator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final FlowAccessControlService flowAccessControlService;

	public SecurityConfig(FlowAccessControlService flowAccessControlService) {
		this.flowAccessControlService = flowAccessControlService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/login", "/error", "/webjars/**", "/ws/**", "/flows/*/tokens/*").permitAll()
				.anyRequest().authenticated()
			)
			.oauth2Login(oauth -> oauth
				.defaultSuccessUrl("/signin/success", true)
				.failureUrl("/login?error=true"))
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler())
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
			)
			.sessionManagement(sessionManagement -> sessionManagement
				.maximumSessions(1)
				.expiredUrl("/login?expired=true")
			);
		return http.build();
	}

	@Bean
	@ConditionalOnProperty(name = "spring.h2.console.enabled", havingValue = "true")
	public WebSecurityCustomizer configureH2ConsoleEnable() {
		return web -> web.ignoring()
			.requestMatchers(PathRequest.toH2Console());
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
		handler.setRedirectStrategy(new DefaultRedirectStrategy());
		handler.setDefaultTargetUrl("/dashboard");
		return handler;
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		// Return HTTP 200 OK on successful logout without redirect
		return new HttpStatusReturningLogoutSuccessHandler();
	}

	@Bean
	public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
		DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
		handler.setPermissionEvaluator(new FlowPermissionEvaluator(flowAccessControlService));
		return handler;
	}
}