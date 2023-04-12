package com.examportal.examportalbackend.controller;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.model.Role;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;
import com.examportal.examportalbackend.service.abstracts.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final MessageSBUtil messageSBUtil;

    UserController(UserService userService,
                   MessageSBUtil messageSBUtil){
        this.userService = userService;
        this.messageSBUtil = messageSBUtil;
    }

    @PostMapping("/create")
    public ResponseEntity<ResultData<User>> create(@Valid @RequestBody User user) throws RuntimeException{
        Role role = new Role(12L,"INSTRUCTOR");
        UserRole userRole = new UserRole(user,role);
        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        return ResponseEntity.ok(this.userService.createUser(user,roles));
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<ResultData<User>> getUser(@Valid @NotEmpty @PathVariable("username") String userName){
        return ResponseEntity.ok(this.userService.getUserByUserName(userName));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@Valid @NotEmpty @PathVariable("id") Long id){
        this.userService.deleteUserById(id);
    }
}
