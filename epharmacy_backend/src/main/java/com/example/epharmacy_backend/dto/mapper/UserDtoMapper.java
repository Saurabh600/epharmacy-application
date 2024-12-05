package com.example.epharmacy_backend.dto.mapper;

import com.example.epharmacy_backend.dto.response.UserDto;
import com.example.epharmacy_backend.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserDtoMapper implements Function<UserInfo, UserDto> {
    private final AddressDtoMapper addressDtoMapper;

    @Override
    public UserDto apply(UserInfo userInfo) {
        return UserDto.builder()
                .userId(userInfo.getUserId())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .emailId(userInfo.getEmailId())
                .age(userInfo.getAge())
                .gender(userInfo.getGender())
                .role(userInfo.getCredential().getRole())
                .address(addressDtoMapper.apply(userInfo.getAddress()))
                .build();
    }
}
