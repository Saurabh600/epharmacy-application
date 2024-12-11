package com.example.epharmacy_backend.dto.response;

import com.example.epharmacy_backend.util.Gender;
import com.example.epharmacy_backend.util.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private int age;
    private Gender gender;
    private Role role;
    private AddressDTO address;
}
