package com.examportal.examportalbackend.auth;

import com.examportal.examportalbackend.security.JwtUtil;
import com.examportal.examportalbackend.service.abstracts.UserService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserService userService;
    public AuthenticationController(AuthenticationService authService, UserService userService,JwtUtil jwtUtils) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest authenticationRequest
    ,@RequestParam(name = "user") String userName){
        return ResponseEntity.ok(this.authService.authenticate(authenticationRequest,userName));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> create(@Valid @RequestBody RegisterRequest registerRequest) throws RuntimeException{
        return ResponseEntity.ok(this.authService.register(registerRequest));
    }


}
