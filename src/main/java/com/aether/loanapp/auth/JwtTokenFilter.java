package com.aether.loanapp.auth;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String username ="";
        String token = "";
        if(authHeader != null && authHeader.contains("Bearer")){
            // 7.karakter
            token = authHeader.substring(7);
            try{
                username = tokenManager.getUsernameToken(token);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        if(username != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null){
            if(tokenManager.tokenValidate(token)){
                UsernamePasswordAuthenticationToken upassToken = new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
                upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upassToken);
            }
        }
        filterChain.doFilter(request , response);
    }
}
