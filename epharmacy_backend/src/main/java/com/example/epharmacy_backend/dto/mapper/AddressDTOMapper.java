package com.example.epharmacy_backend.dto.mapper;

import com.example.epharmacy_backend.dto.response.AddressDTO;
import com.example.epharmacy_backend.entity.Address;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AddressDTOMapper implements Function<Address, AddressDTO> {
    @Override
    public AddressDTO apply(Address address) {
        return AddressDTO.builder()
                .pinCode(address.getPinCode())
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }
}
