package com.example.user.controller;

import com.example.user.dto.LoginRequestDTO;
import com.example.user.dto.UserCreateVehicleDTO;
import com.example.user.dto.UserDTO;
import com.example.user.model.Notification;
import com.example.user.model.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * GET /user/userExists
     *
     * @return boolean value which indicates user existence
     */
    @GetMapping(value = "/usernames", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsernames() throws Exception {
        System.out.println("-------- tu sam -------");
        List<User> users = userService.getUsers();
        System.out.println("-------- useri -------" + users);
        List<UserDTO> usernamesList = userService.convertUserToUserDTO(users);
        System.out.println("-------- usernames -------" + usernamesList);
        return new ResponseEntity<List<UserDTO>>(usernamesList, HttpStatus.OK);
    }


    /**
     * GET /user/userExists
     *
     * @return boolean value which indicates user existence
     */
    @GetMapping(value = "/userExists", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> userExists(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        Boolean userExists = userService.userExists(loginRequestDTO);
        return new ResponseEntity<Boolean>(userExists, HttpStatus.OK);
    }

    /**
     * GET /user/username
     *
     * @return returns object of type UserDTO with user id and username
     */
    @GetMapping(value = "/username", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUsername(@RequestBody UserDTO userDTO) throws Exception {
        UserDTO userInfo = userService.getUsername(userDTO);
        return new ResponseEntity<UserDTO>(userInfo, HttpStatus.OK);
    }

    /**
     * GET /user/canUserCreate/{userId}
     *
     * @return returns true if user can create vehicles
     */
    @GetMapping(value = "canUserCreate/{userId}", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createVehicleValidation(@PathVariable Long userId) throws Exception {
        Boolean userInfo = userService.canUserCreate(userId);
        return new ResponseEntity<Boolean>(userInfo, HttpStatus.OK);
    }

    /**
     * PUT /user/updateUserVehicleNumAfterCreate/{userId}
     *
     * @return updates user vehicle number after create
     */
//    @PutMapping(value = "updateUserVehicleNumAfterCreate/{userId}", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Notification> updateUserVehicleNumAfterCreate(@PathVariable Long userId) throws Exception {
//        Notification notification = userService.updateUserVehicleNumAfterCreate(userId);
//        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
//    }
}
