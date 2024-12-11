package com.example.epharmacy_backend.controller;

import com.example.epharmacy_backend.dto.request.UserReqDTO;
import com.example.epharmacy_backend.dto.request.AuthReqDTO;
import com.example.epharmacy_backend.dto.response.ErrorDTO;
import com.example.epharmacy_backend.dto.response.UserDTO;
import com.example.epharmacy_backend.service.JwtService;
import com.example.epharmacy_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserReqDTO dto) {
        var newUser = userService.createNewUser(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> generateToken(@RequestBody @Valid AuthReqDTO dto) {
        try {
            userService.verify(dto);
            String token = jwtService.generateToken(dto.getEmailId());
            var userData = userService.getUserByEmailId(dto.getEmailId());

            var res = new HashMap<String, Object>();
            res.put("token", token);
            res.put("data", userData);

            return ResponseEntity.ok(res);
        } catch (AuthenticationException e) {
            var res = ErrorDTO.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED.toString())
                    .message("credential failed!")
                    .error("invalid username or password!")
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }
}
