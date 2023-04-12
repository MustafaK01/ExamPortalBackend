package com.examportal.examportalbackend.controller;

import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.model.Role;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;
import com.examportal.examportalbackend.service.abstracts.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResultData<User> create(@RequestBody User user) throws RuntimeException{
        Role role = new Role(12L,"INSTRUCTOR");
        UserRole userRole = new UserRole(user,role);
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        return this.userService.createUser(user,roles);
    }

}
