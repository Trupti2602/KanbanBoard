package com.cg.serviceImpl;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;
import com.cg.repository.UserRepository;
import com.cg.service.UserService;



@Service
public class UserServiceImpl implements UserService{
	
private UserRepository userRepository;
	
	static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) throws UserNotFoundException {
        String emailId = user.getEmailId();
        // Check if the emailId already exists in the repository
        if (userRepository.findByEmailId(emailId) != null) {
            throw new UserNotFoundException("User with emailId " + emailId + " already exists");
        }
        // Save the user
        return userRepository.save(user);
    }
    

//    @Override
//    public User loginUser(String emailId, String password) throws UserNotFoundException {
//        User user = userRepository.findByEmailIdAndPassword(emailId, password);
//        if (user == null) {
//            throw new UserNotFoundException("Invalid email or password");
//        }
//        return user;
//    }
    
    @Override
    public String loginUser(String emailId, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailId(emailId);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid email or password");
        }
        return "Login successful";
    }
    
    @Override
    public List<User> viewAll() throws UserNotFoundException {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return userList;
    }

    
    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }
}