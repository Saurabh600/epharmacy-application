package com.example.epharmacy_backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private String pinCode;
    private String street;
    private String city;
    private String country;
}