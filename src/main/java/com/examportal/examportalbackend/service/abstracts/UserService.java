package com.examportal.examportalbackend.service.abstracts;

import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.model.UserRole;

import java.util.Set;

public interface UserService {

    public ResultData<User> createUser(User user, Set<UserRole> userRoles) throws RuntimeException;

}
