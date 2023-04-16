package com.examportal.examportalbackend.security;

import com.examportal.examportalbackend.core.utils.MessageSBUtil;
import com.examportal.examportalbackend.exception.UserNotFoundException;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final MessageSBUtil messageSBUtil;

    public UserDetailsServiceImpl(UserRepository userRepository
            , MessageSBUtil messageSBUtil) {
        this.userRepository = userRepository;
        this.messageSBUtil = messageSBUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
         Optional<User> user = this.userRepository.findUserByUserName(userName);
         if(user.isEmpty()) throw new UserNotFoundException(messageSBUtil.getMessage("USER_NOT_FOUND"));
         return user.get();
    }
}
