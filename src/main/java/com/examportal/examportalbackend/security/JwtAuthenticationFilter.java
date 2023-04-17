package com.examportal.examportalbackend.security;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final MessageSBUtil messageSBUtil;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil, MessageSBUtil messageSBUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.messageSBUtil = messageSBUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenJwt = extractFromReq(request);
            if (StringUtils.hasText(tokenJwt) && jwtUtil.validateToken(tokenJwt)){
                String userName = jwtUtil.getUserNameFromJwt(tokenJwt);
                final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);                //if not null, authenticate it
                if(userDetails!=null){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            };
        }catch (Exception e ){
            return;
        }
        filterChain.doFilter(request,response);
    }

    private String extractFromReq(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")){
            return bearer.substring("Bearer".length()+1);
        };
        return null;
    }
}