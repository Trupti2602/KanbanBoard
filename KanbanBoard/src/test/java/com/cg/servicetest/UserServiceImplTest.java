package com.cg.servicetest;

import static org.junit.jupiter.api.Assertions.*;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;
import com.cg.repository.UserRepository;
import com.cg.serviceImpl.UserServiceImpl;

class UserServiceImplTest {

	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

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

        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(null);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser(user);

        Assertions.assertEquals(user, registeredUser);
    }

    @Test
    public void testRegisterUserWithEmailIdAlreadyExists() {
        User user = new User();
        user.setId(1);
        user.setfName("John");
        user.setEmailId("john@example.com");

        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user);

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.registerUser(user));
    }

    @Test
    public void testLoginUserWithValidCredentials() throws UserNotFoundException {
        User user = new User();
        user.setId(1);
        user.setfName("John");
        user.setEmailId("john@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user);

        String result = userService.loginUser("john@example.com", "password");

        Assertions.assertEquals("Login successful", result);
    }

    @Test
    public void testLoginUserWithInvalidEmail() {
        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.loginUser("john@example.com", "password"));
    }

    @Test
    public void testLoginUserWithInvalidPassword() {
        User user = new User();
        user.setId(1);
        user.setfName("John");
        user.setEmailId("john@example.com");
        user.setPassword("password");

        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user);

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.loginUser("john@example.com", "invalidpassword"));
    }

    @Test
    public void testViewAllUsers() throws UserNotFoundException {
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

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.viewAll();

        Assertions.assertEquals(userList, result);
    }

    @Test
    public void testViewAllUsersWhenNoUsersFound() {
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.viewAll());
        
    }

    @Test
    public void testGetUserByIdWithValidId() throws UserNotFoundException {
        User user = new User();
        user.setId(1);
        user.setfName("John");
        user.setEmailId("john@example.com");

        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        Assertions.assertEquals(user, result);
    }

    @Test
    public void testGetUserByIdWithInvalidId() {
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(1));
    }
}

//    @Test
//    public void testRegisterUser() throws UserNotFoundException {
//        
//        User user = new User();
//        user.setId(1);
//        user.setfName("John");
//        user.setEmailId("john@example.com");
//
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
//
//        User registeredUser = userService.registerUser(user);
//
//        Assertions.assertEquals(user, registeredUser);
//    }
//
//    @Test
//    public void testLoginUserWithValidCredentials() throws UserNotFoundException {
//        User user = new User();
//        user.setId(1);
//        user.setfName("John");
//        user.setEmailId("john@example.com");
//        user.setPassword("password");
//
//        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user);
//
//        String result = userService.loginUser("john@example.com", "password");
//
//        Assertions.assertEquals("Login successful", result);
//    }
//
//    @Test
//    public void testLoginUserWithInvalidEmail() {
//       
//        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(null);
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.loginUser("john@example.com", "password");
//        });
//    }
//
//    @Test
//    public void testLoginUserWithInvalidPassword() {
//        
//        User user = new User();
//        user.setId(1);
//        user.setfName("John");
//        user.setEmailId("john@example.com");
//        user.setPassword("password");
//
//        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user);
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.loginUser("john@example.com", "invalidpassword");
//        });
//    }
//
//    @Test
//    public void testViewAllUsersWithExistingUsers() throws UserNotFoundException {
//        
//        List<User> userList = new ArrayList<>();
//
//        User user1 = new User();
//        user1.setId(1);
//        user1.setfName("John");
//        user1.setEmailId("john@example.com");
//        userList.add(user1);
//
//        User user2 = new User();
//        user2.setId(2);
//        user2.setfName("Jane");
//        user2.setEmailId("jane@example.com");
//        userList.add(user2);
//
//        Mockito.when(userRepository.findAll()).thenReturn(userList);
//
//        List<User> result = userService.viewAll();
//
//        Assertions.assertEquals(userList, result);
//    }
//
//    @Test
//    public void testViewAllUsersWithNoUsers() {
//       
//        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());
//
//       
//        Assertions.assertThrows(UserNotFoundException.class, () -> {
//            userService.viewAll();
//        });
//    }
//
//    @Test
//    public void testGetUserByIdWithExistingId() throws UserNotFoundException {
//       
//        User user = new User();
//        user.setId(1);
//        user.setfName("John");
//        user.setEmailId("john@example.com");
//
//        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
//
//        User result = userService.getUserById(1);
//
//        Assertions.assertEquals(user, result);
//    }
//
//    @Test
//    public void testGetUserByIdWithNonExistingId() {
//        
//        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.getUserById(1);
//        });
//    }
//}