package com.example.epharmacy_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressReqDto {
    @Pattern(regexp = "^[1-9]\\d{5}$", message = "pinCode must of 6 digits")
    private String pinCode;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String country;
}
