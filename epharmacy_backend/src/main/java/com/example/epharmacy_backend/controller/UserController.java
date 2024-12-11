package com.example.epharmacy_backend.controller;

import com.example.epharmacy_backend.dto.response.UserDTO;
import com.example.epharmacy_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("user_id") long userId) {
        var userData = userService.getUserById(userId);
        return ResponseEntity.ok(userData);
    }
}
