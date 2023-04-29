package com.examportal.examportalbackend.auth;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import com.examportal.examportalbackend.dto.AuthenticatedUserDto;
import com.examportal.examportalbackend.exception.InvalidParamException;
import com.examportal.examportalbackend.exception.UserNotFoundException;
import com.examportal.examportalbackend.model.Role;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;
import com.examportal.examportalbackend.model.enums.Roles;
import com.examportal.examportalbackend.repository.RoleRepository;
import com.examportal.examportalbackend.repository.UserRepository;
import com.examportal.examportalbackend.security.JwtUtil;
import com.examportal.examportalbackend.service.abstracts.InstructorService;
import com.examportal.examportalbackend.service.abstracts.StudentService;
import com.examportal.examportalbackend.service.abstracts.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MessageSBUtil messageSBUtil;
    private final InstructorService instructorService;

    private final StudentService studentService;


    public AuthenticationService(UserDetailsService userDetailsService, UserService userService
            , PasswordEncoder passwordEncoder, JwtUtil jwtUtil
            , AuthenticationManager authenticationManager
            , UserRepository userRepository, RoleRepository roleRepository, MessageSBUtil messageSBUtil, InstructorService instructorService, StudentService studentService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.messageSBUtil = messageSBUtil;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest,String userName) {
        if(!userName.equals(authenticationRequest.getUserName())) throw new UserNotFoundException(this.messageSBUtil.getMessage("USER_NOT_FOUND"));
        var user = userRepository.findUserByUserName(authenticationRequest.getUserName())
                .orElseThrow(()-> new UserNotFoundException(this.messageSBUtil.getMessage("USER_NOT_FOUND")));
        return this.auth(authenticationRequest,user);
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
            User user = new User();
            user.setUser(registerRequest,user);
            this.userService.createUser(user
                    ,this.setUserRoles(registerRequest.getRoleName(), user));
            var jwtToken = jwtUtil.generateToken(user);
            this.isStudentOrInstructor(registerRequest,user);
            Long expirationDate = jwtUtil.expirationDate(jwtToken);
            return new AuthenticationResponse(jwtToken,expirationDate);

    }

    private AuthenticationResponse auth(AuthenticationRequest authenticationRequest,User user){
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName()
                        ,authenticationRequest.getPassword())));
        var jwtToken = jwtUtil.generateToken(user);
        Long expirationDate = jwtUtil.expirationDate(jwtToken);
        return new AuthenticationResponse(
                jwtToken
                ,expirationDate
                ,this.getAuthenticatedUser());
    }

    public AuthenticatedUserDto getAuthenticatedUser() {
        User user = (User) this.userDetailsService.loadUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        return AuthenticatedUserDto.convertToDto(user);
    }

    private Set<UserRole> setUserRoles(String roleName,User user){
        Optional<Role> role = this.roleRepository.findRoleByRoleName(roleName);
        if(role.isPresent()){
            UserRole userRole = new UserRole(user,role.get());
            Set<UserRole> roles = new HashSet<>();
            roles.add(userRole);
            return roles;
        }else throw new InvalidParamException(this.messageSBUtil.getMessage("INVALID_PARAMETER"));
    }

    public void isStudentOrInstructor(RegisterRequest registerRequest,User user){
        if (Objects.equals(registerRequest.getRoleName(), Roles.INSTRUCTOR.toString())) {
            this.instructorService.addInstructor(user);
        } else {
            this.studentService.addStudent(user);
        }
    }



}
