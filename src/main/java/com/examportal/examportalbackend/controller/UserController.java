package com.examportal.examportalbackend.controller;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.core.utils.results.ResultDataSuccess;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.service.abstracts.UserService;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/getUser/{username}")
    public ResponseEntity<ResultData<User>> getUser(@Valid @NotEmpty @PathVariable("username") String userName){
        return ResponseEntity.ok(this.userService.getUserByUserName(userName));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@Valid @NotEmpty @PathVariable("id") Long id){
        this.userService.deleteUserById(id);
    }

    @GetMapping("/testAuth")
    public ResultData<String> testAuth(@RequestParam(name="user") String user){
        return new ResultDataSuccess<>("Hello") ;
    }

}
