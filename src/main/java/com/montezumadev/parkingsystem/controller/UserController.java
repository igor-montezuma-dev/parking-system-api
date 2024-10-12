package com.montezumadev.parkingsystem.controller;

import com.montezumadev.parkingsystem.entity.User;
import com.montezumadev.parkingsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createNewUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }
}
