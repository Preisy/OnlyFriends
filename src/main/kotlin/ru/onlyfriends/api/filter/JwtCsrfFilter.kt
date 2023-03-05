package ru.onlyfriends.api.filter

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.InvalidCsrfTokenException
import org.springframework.security.web.csrf.MissingCsrfTokenException
import org.springframework.security.web.util.UrlUtils
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.servlet.HandlerExceptionResolver
//import ru.onlyfriends.api.security.JwtTokenRepository
import java.io.IOException


//class JwtCsrfFilter(
//    private val tokenRepository: CsrfTokenRepository,
//    private val resolver: HandlerExceptionResolver
//) : OncePerRequestFilter() {
//    @Throws(ServletException::class, IOException::class)
//    override fun doFilterInternal(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        request.setAttribute(HttpServletResponse::class.java.name, response)
//        var csrfToken: CsrfToken? = tokenRepository.loadToken(request)
//        if (csrfToken == null) {
//            csrfToken = tokenRepository.generateToken(request)
//            tokenRepository.saveToken(csrfToken, request, response)
//        }
//        request.setAttribute(CsrfToken::class.java.name, csrfToken)
//        request.setAttribute(csrfToken!!.parameterName, csrfToken)
//        if (request.servletPath == "/login") {
//            try {
//                filterChain.doFilter(request, response)
//            } catch (e: Exception) {
//                resolver.resolveException(request, response, null, MissingCsrfTokenException(csrfToken.getToken()))
//            }
//        } else {
//            var actualToken = request.getHeader(csrfToken.headerName)
//            if (actualToken == null) {
//                actualToken = request.getParameter(csrfToken.parameterName)
//            }
//            try {
//                if (actualToken.isNotEmpty()) {
//                    Jwts.parser()
//                        .setSigningKey((tokenRepository as JwtTokenRepository).secret)
//                        .parseClaimsJws(actualToken)
//                    filterChain.doFilter(request, response)
//                } else resolver.resolveException(
//                    request,
//                    response, null, InvalidCsrfTokenException(csrfToken, actualToken)
//                )
//            } catch (e: JwtException) {
//                if (logger.isDebugEnabled) {
//                    logger.debug("Invalid CSRF token found for " + UrlUtils.buildFullRequestUrl(request))
//                }
//                if (csrfToken == null) {
//                    resolver.resolveException(request, response, null, MissingCsrfTokenException(actualToken))
//                } else {
//                    resolver.resolveException(
//                        request,
//                        response, null, InvalidCsrfTokenException(csrfToken, actualToken)
//                    )
//                }
//            }
//        }
//    }
//}