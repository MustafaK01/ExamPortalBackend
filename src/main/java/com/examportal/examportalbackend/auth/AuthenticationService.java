package com.examportal.examportalbackend.auth;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import com.examportal.examportalbackend.exception.UserNotFoundException;
import com.examportal.examportalbackend.model.Role;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;
import com.examportal.examportalbackend.repository.UserRepository;
import com.examportal.examportalbackend.security.JwtUtil;
import com.examportal.examportalbackend.service.abstracts.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final MessageSBUtil messageSBUtil;


    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserRepository userRepository, MessageSBUtil messageSBUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.messageSBUtil = messageSBUtil;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest,String userName) {
        if(!userName.equals(authenticationRequest.getUserName())) throw new UserNotFoundException(this.messageSBUtil.getMessage("USER_NOT_FOUND"));
        var user = userRepository.findUserByUserName(authenticationRequest.getUserName())
                .orElseThrow(()-> new UserNotFoundException(this.messageSBUtil.getMessage("USER_NOT_FOUND")));
        return this.auth(authenticationRequest,user);
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = new User(0L,registerRequest.getUserName()
                , passwordEncoder.encode(registerRequest.getPassword())
                , registerRequest.getFirstName(),
                registerRequest.getLastName()
                ,registerRequest.getEmail(), registerRequest.getPhone(),true,"asd");
        Role role = new Role(12L,"INSTRUCTOR");
        UserRole userRole = new UserRole(user,role);
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        this.userService.createUser(user,roles);
        var jwtToken = jwtUtil.generateToken(user);
        Long expirationDate = jwtUtil.expirationDate(jwtToken);
        return new AuthenticationResponse(jwtToken,expirationDate);
    }

    private AuthenticationResponse auth(AuthenticationRequest authenticationRequest,User user){
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName()
                        ,authenticationRequest.getPassword())));
        var jwtToken = jwtUtil.generateToken(user);
        Long expirationDate = jwtUtil.expirationDate(jwtToken);
        return new AuthenticationResponse(jwtToken,expirationDate);
    }

}
