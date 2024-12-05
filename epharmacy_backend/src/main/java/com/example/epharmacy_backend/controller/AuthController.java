package com.example.epharmacy_backend.controller;

import com.example.epharmacy_backend.dto.request.UserRegisterDto;
import com.example.epharmacy_backend.dto.request.UsernamePasswordDto;
import com.example.epharmacy_backend.dto.response.ApiErrorRes;
import com.example.epharmacy_backend.dto.response.UserDto;
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
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserRegisterDto dto) {
        var newUser = userService.createNewUser(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> generateToken(@RequestBody @Valid UsernamePasswordDto dto) {
        try {
            userService.verify(dto);
            String token = jwtService.generateToken(dto.getEmailId());
            var userData = userService.getUserByEmailId(dto.getEmailId());

            var res = new HashMap<String, Object>();
            res.put("token", token);
            res.put("data", userData);

            return ResponseEntity.ok(res);
        } catch (AuthenticationException e) {
            var res = ApiErrorRes.builder()
                    .httpStatus(HttpStatus.UNAUTHORIZED.toString())
                    .message("credential failed!")
                    .error("invalid username or password!")
                    .build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }
}
