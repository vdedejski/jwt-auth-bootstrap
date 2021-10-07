package com.vdedejski.jwtauthbootstrap.web.controller;

import com.vdedejski.jwtauthbootstrap.model.User;
import com.vdedejski.jwtauthbootstrap.model.dto.UserDto;
import com.vdedejski.jwtauthbootstrap.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto user) {
        System.out.println(user.toString());
        User createdUser =   this.userService.register(user);
        return ResponseEntity.ok(createdUser);
    }
}
