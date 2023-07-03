package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;
import com.cg.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        User registeredUser = userService.registerUser(user);
//        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
//    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            // Handle the exception and return an appropriate response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("emailId") String emailId,
                                            @RequestParam("password") String password) {
        try {
            userService.loginUser(emailId, password);
            return ResponseEntity.ok("Login successful");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    
    @GetMapping("/reg")
    public ResponseEntity<List<User>> getAll() throws UserNotFoundException{
        return new ResponseEntity<>(userService.viewAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFoundException {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}



