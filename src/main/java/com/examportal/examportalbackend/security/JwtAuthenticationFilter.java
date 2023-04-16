package com.examportal.examportalbackend.security;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import com.examportal.examportalbackend.exception.InvalidTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MessageSBUtil messageSBUtil;

    public JwtAuthenticationFilter(UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, MessageSBUtil messageSBUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.messageSBUtil = messageSBUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String tokenHeader = request.getHeader("Authorization");
        String userName = null;
        String jwtToken = null;
        if(tokenHeader !=null && tokenHeader.startsWith("Bearer ")){
            jwtToken = tokenHeader.substring(7);
            try{
                userName = this.jwtUtil.extractUsername(jwtToken);
            }catch (ExpiredJwtException e){
                e.printStackTrace();
            }
        }else{
            throw new InvalidTokenException(this.messageSBUtil.getMessage("INVALID_TOKEN"));
        }
        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if(this.jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken uFilter
                        = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                uFilter.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(uFilter);
            }
        }else{
            throw new InvalidTokenException(this.messageSBUtil.getMessage("INVALID_TOKEN"));
        }
        filterChain.doFilter(request,response);
    }
}