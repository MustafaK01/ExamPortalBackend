package com.examportal.examportalbackend;

import com.examportal.examportalbackend.model.Role;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;
import com.examportal.examportalbackend.repository.RoleRepository;
import com.examportal.examportalbackend.repository.UserRepository;
import com.examportal.examportalbackend.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamPortalBackendApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ExamPortalBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started");
        User user = new User(0L,"asd","asd","asd","aasd","aasd","aasd",true,"aasd");
        Role role = new Role(1L,"Admin");
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoles.add(userRole);
        userService.createUser(user,userRoles);

    }

}
