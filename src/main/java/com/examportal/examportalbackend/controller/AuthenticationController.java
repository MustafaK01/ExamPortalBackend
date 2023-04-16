package com.examportal.examportalbackend.controller;

import com.examportal.examportalbackend.exception.UserNotFoundException;
import com.examportal.examportalbackend.security.AuthenticationRequest;
import com.examportal.examportalbackend.security.AuthenticationResponse;
import com.examportal.examportalbackend.security.JwtUtil;
import com.examportal.examportalbackend.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
//
    public AuthenticationController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtils;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> generateToken (@RequestBody AuthenticationRequest authenticationRequest){
        try{
            this.authenticate(authenticationRequest.getUserName(),authenticationRequest.getPassword());

        }catch (UserNotFoundException e){
            // Throw Exception
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println(token);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }


    private void authenticate (String userName, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        }catch (DisabledException e ){
            // Throw Exception
        }catch (BadCredentialsException e ){
            // Throw Exception
        }
    }

}
