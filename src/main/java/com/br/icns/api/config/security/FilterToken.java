package com.br.icns.api.config.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.br.icns.api.services.user.TokenService;
import com.br.icns.api.services.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token;
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader!=null){

            try {
                String subject = this.tokenService.getSubject(authorizationHeader);
                var user= this.userService.findByUsername(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (TokenExpiredException e) {
                System.out.println(e.getMessage());
            }
        }

        filterChain.doFilter(request,response);
    }
}
