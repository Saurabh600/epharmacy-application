package com.example.epharmacy_backend.dto.request;

import com.example.epharmacy_backend.util.Gender;
import com.example.epharmacy_backend.util.Role;
import com.example.epharmacy_backend.validation.EnumValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "emailId is required")
    @Email(message = "please provide a valid emailId")
    private String emailId;

    @Min(value = 16, message = "you must be of at least 16 years")
    @Max(value = 110, message = "age can not be more than 110 years")
    private int age;

    @EnumValidator(enumClazz = Gender.class, message = "invalid gender")
    private String gender;

    @NotBlank(message = "password is required")
    @Size(min = 7, max = 15, message = "password must between 7 to 15 characters")
    private String password;

    @EnumValidator(enumClazz = Role.class, message = "invalid role")
    private String role;

    @NotNull(message = "address is required")
    @Valid
    private AddressBodyDto address;
}
