package ru.onlyfriends.api.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import ru.onlyfriends.api.filter.JwtAuthenticationFilter
import ru.onlyfriends.api.filter.JwtAuthorizationFilter
import ru.onlyfriends.api.model.dto.exception.UnauthorizedException
import ru.onlyfriends.api.utils.JwtTokenUtil

@Configuration
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
//    fun securityException401EntryPoint(): Http401AuthenticationEntryPoint? {
//        return Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\"")
//    }


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)
        http
            .csrf()
                .disable()
            .authorizeHttpRequests()
                .requestMatchers("/health", "/login", "/signup").permitAll()
                .requestMatchers("/users/**").authenticated()
                .anyRequest().authenticated()
            .and()
                .exceptionHandling().authenticationEntryPoint(UnauthorizedException())
            .and()
                .authenticationManager(authenticationManager)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(JwtAuthenticationFilter(jwtToken, authenticationManager))
                .addFilter(JwtAuthorizationFilter(jwtToken, userDetailsService, authenticationManager))
        return http.build()
    }

}