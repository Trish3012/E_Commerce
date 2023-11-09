package com.app.service;

import com.app.exception.UserException;
import com.app.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;
	
	public User findUserByJwt(String jwt) throws UserException;
}
