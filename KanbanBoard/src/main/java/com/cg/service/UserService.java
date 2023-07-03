package com.cg.service;

import java.util.List;



import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;

public interface UserService {
	
	User registerUser(User user) throws UserNotFoundException;
    String loginUser(String emailId, String password) throws UserNotFoundException;
    User getUserById(int id) throws UserNotFoundException;
    public List<User> viewAll() throws UserNotFoundException;
	
}