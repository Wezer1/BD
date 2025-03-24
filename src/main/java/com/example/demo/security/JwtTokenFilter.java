package com.example.demo.security;

import com.example.demo.exceptions.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    // TODO: 29.03.2024 Я был не прав. Мы всегда будем попдать в этот фильтер, даже если мы помели путь до контроллера как permitAll. Чтобы корректно работал пришлось поменять метод resolveToken


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, JwtAuthenticationException {

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        if(token !=null){
            if(jwtTokenProvider.validateToken(token)){
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                //поскольку переписал метод и возвращаю при ошибке null сделал выброс ошибки
                else{
                    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
                }
            }else{
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value());
            }
        }else{

        }
        filterChain.doFilter(servletRequest, servletResponse);

    }


}
