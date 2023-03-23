package ru.onlyfriends.api.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import ru.onlyfriends.api.filter.JwtAuthenticationFilter
import ru.onlyfriends.api.filter.JwtAuthorizationFilter
import ru.onlyfriends.api.model.dto.exception.UnauthorizedException
import ru.onlyfriends.api.model.dto.exception.ForbiddenException
import ru.onlyfriends.api.utils.JwtTokenUtil


@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
) {
    private val jwtToken = JwtTokenUtil()

    private fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userDetailsService)
        return authenticationManagerBuilder.build()
    }

//    @Bean
//    fun methodSecurityExpressionHandler(): MethodSecurityExpressionHandler? {
//        val expressionHandler = DefaultMethodSecurityExpressionHandler()
//        expressionHandler.setPermissionEvaluator(CustomPermissionEvaluator())
//        return expressionHandler
//    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)
        http
            .csrf()
                .disable()
            .authorizeHttpRequests()
                .requestMatchers("/health", "/login", "/signup").permitAll()
                .requestMatchers("/users").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(UnauthorizedException())
                .accessDeniedHandler(ForbiddenException())
            .and()
                .authenticationManager(authenticationManager)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(JwtAuthenticationFilter(jwtToken, authenticationManager))
                .addFilter(JwtAuthorizationFilter(jwtToken, userDetailsService, authenticationManager))
        return http.build()
    }

}