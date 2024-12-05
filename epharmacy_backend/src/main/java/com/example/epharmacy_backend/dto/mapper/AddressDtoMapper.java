package com.example.epharmacy_backend.dto.mapper;

import com.example.epharmacy_backend.dto.response.AddressDto;
import com.example.epharmacy_backend.entity.Address;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AddressDtoMapper implements Function<Address, AddressDto> {
    @Override
    public AddressDto apply(Address address) {
        return AddressDto.builder()
                .pinCode(address.getPinCode())
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }
}
