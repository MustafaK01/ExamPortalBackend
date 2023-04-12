package com.examportal.examportalbackend.service.concrete;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.core.utils.results.ResultDataSuccess;
import com.examportal.examportalbackend.exception.UserAlreadyExistsException;
import com.examportal.examportalbackend.exception.UserNotFoundException;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;
import com.examportal.examportalbackend.repository.RoleRepository;
import com.examportal.examportalbackend.repository.UserRepository;
import com.examportal.examportalbackend.service.abstracts.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MessageSBUtil messageSBUtil;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository
                        , MessageSBUtil messageSBUtil){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.messageSBUtil = messageSBUtil;
    }

    @Override
    public ResultData<User> createUser(User user, Set<UserRole> userRoles) throws RuntimeException {
        if(userRepository.findUserByUserName(user.getUserName()).isPresent()){
            throw new UserAlreadyExistsException(
                    messageSBUtil.getMessage("USER_EXISTS"));
        }else{
            for (UserRole userRole: userRoles) roleRepository.save(userRole.getRole());
            user.getUserRoles().addAll(userRoles);
            return new ResultDataSuccess<>(this.userRepository.save(user)
                    ,messageSBUtil.getMessage("USER_CREATED"));
        }
    }

    @Override
    public ResultData<User> getUserByUserName(String userName) {
        return new ResultDataSuccess<>(this.userRepository.findUserByUserName(userName).orElseThrow(
                ()->new UserNotFoundException(messageSBUtil.getMessage("USER_NOT_FOUND"))));
    }

    @Override
    public void deleteUserById(Long id) {
         this.userRepository.deleteUserByUserId(id).orElseThrow(
                ()->new UserNotFoundException(messageSBUtil.getMessage("USER_NOT_FOUND")));
    }
}
