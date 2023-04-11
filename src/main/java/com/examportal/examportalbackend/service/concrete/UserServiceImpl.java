package com.examportal.examportalbackend.service.concrete;

import com.examportal.examportalbackend.exception.UserAlreadyExistsException;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;
import com.examportal.examportalbackend.repository.RoleRepository;
import com.examportal.examportalbackend.repository.UserRepository;
import com.examportal.examportalbackend.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        if(userRepository.findUserByUserName(user.getUserName()).isPresent()){
            //throw new UserAlreadyExistsException("User Already Exists");
            System.out.println("User Already Exists");
            return null;
        }else{
            for (UserRole userRole: userRoles) roleRepository.save(userRole.getRole());
            user.getUserRoles().addAll(userRoles);
            return this.userRepository.save(user);
        }
    }
}
