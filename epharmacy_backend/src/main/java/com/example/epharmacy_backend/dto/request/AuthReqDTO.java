package com.example.epharmacy_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthReqDTO {
    @NotBlank(message = "emailId is required")
    private String emailId;

    @NotBlank(message = "password is required")
    private String password;
}
