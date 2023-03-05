package ru.onlyfriends.api.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.web.servlet.HandlerExceptionResolver
//import ru.onlyfriends.api.filter.JwtCsrfFilter
import ru.onlyfriends.api.service.userService.UserService
//import ru.onlyfriends.api.security.JwtTokenRepository

//
//@Configuration
//@EnableWebSecurity
//class SecurityConfiguration(
//    private val service: UserService,
//    private val jwtTokenRepository: JwtTokenRepository
//) {
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private val resolver: HandlerExceptionResolver? = null
////    @Bean
////    fun devPasswordEncoder(): PasswordEncoder {
////        return NoOpPasswordEncoder.getInstance()
////    }
//
//    @Bean
//    @Throws(java.lang.Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
//        http
//                .authorizeHttpRequests().requestMatchers("/users")
//                .permitAll().anyRequest().authenticated()
//            .and()
//                .httpBasic()
////                .authenticationEntryPoint { request, response, e ->
////                resolver!!.resolveException(
////                    request,
////                    response,
////                    null,
////                    e
////                )
////            }
//            .and()
//                .csrf().ignoringRequestMatchers("/**")
//            .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//            .and()
//                .addFilterAt(JwtCsrfFilter(jwtTokenRepository
//                    , resolver!!
//                ), CsrfFilter::class.java)
//
////        http
////            .sessionManagement()
////            .sessionCreationPolicy(SessionCreationPolicy.NEVER)
////            .and()
////            .addFilterAt(JwtCsrfFilter(jwtTokenRepository, resolver!!), CsrfFilter::class.java)
////            .csrf().ignoringAntMatchers("/**")
////            .and()
////            .authorizeRequests()
////            .antMatchers("/auth/login")
////            .authenticated()
////            .and()
////            .httpBasic()
////            .authenticationEntryPoint { request, response, e ->
////                resolver!!.resolveException(
////                    request,
////                    response,
////                    null,
////                    e
////                )
////            }
//
//
//        return http.build()
//    }
//
////    @Throws(Exception::class)
////    protected fun configure(auth: AuthenticationManagerBuilder) {
////        auth.userDetailsService(service)
////    }
//}