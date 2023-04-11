package com.examportal.examportalbackend.service.abstracts;

import com.examportal.examportalbackend.exception.UserAlreadyExistsException;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;

import java.util.Set;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws UserAlreadyExistsException;

}
