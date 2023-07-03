package com.cg.controllertest;

import java.util.ArrayList;
import java.util.List;

import com.cg.controller.UserController;

import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;
import com.cg.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {
	@Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() throws UserNotFoundException {
       
        User user = new User();
        user.setId(1);
        user.setfName("John");
        user.setEmailId("john@example.com");

        Mockito.when(userService.registerUser(Mockito.any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.registerUser(user);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());
    }

//    @Test
//    public void testLoginUserWithValidCredentials() throws UserNotFoundException {
//        // Mock the userService.loginUser() method to not throw UserNotFoundException
//        Mockito.doNothing().when(userService).loginUser(Mockito.anyString(), Mockito.anyString());
//
//        // Call the loginUser method of the userController with valid credentials
//        ResponseEntity<String> response = userController.loginUser("john@example.com", "password");
//
//        // Assert that the response status is HttpStatus.OK
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertEquals("Login successful", response.getBody());
//    }

    @Test
    public void testLoginUserWithInvalidCredentials() throws UserNotFoundException {
        
        Mockito.doThrow(UserNotFoundException.class)
                .when(userService)
                .loginUser(Mockito.anyString(), Mockito.anyString());

        
        ResponseEntity<String> response = userController.loginUser("john@example.com", "password");

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        Assertions.assertEquals("Invalid email or password", response.getBody());
    }

    @Test
    public void testGetAllUsers() throws UserNotFoundException {
        
        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setfName("John");
        user1.setEmailId("john@example.com");
        userList.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setfName("Jane");
        user2.setEmailId("jane@example.com");
        userList.add(user2);

        Mockito.when(userService.viewAll()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAll();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(userList, response.getBody());
    }

    @Test
    public void testGetUserByIdWithValidId() throws UserNotFoundException {
        
        User user = new User();
        user.setId(1);
        user.setfName("John");
        user.setEmailId("john@example.com");

        Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(user, response.getBody());
    }

//    @Test
//    public void testGetUserByIdWithInvalidId() throws UserNotFoundException {
//        // Mock the userService.getUserById() method to throw UserNotFoundException
//        Mockito.doThrow(UserNotFoundException.class)
//                .when(userService)
//                .getUserById(Mockito.anyInt());
//
//        // Call the getUserById method of the userController with invalid ID
//        ResponseEntity<User> response = userController.getUserById(1);
//
//        // Assert that the response status is HttpStatus.NOT_FOUND
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        Assertions.assertNull(response.getBody());
//    }
}