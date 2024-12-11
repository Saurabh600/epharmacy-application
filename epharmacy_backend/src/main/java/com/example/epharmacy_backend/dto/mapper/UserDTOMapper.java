package com.example.epharmacy_backend.dto.mapper;

import com.example.epharmacy_backend.dto.response.UserDTO;
import com.example.epharmacy_backend.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserDTOMapper implements Function<UserInfo, UserDTO> {
    private final AddressDTOMapper addressDtoMapper;

    @Override
    public UserDTO apply(UserInfo userInfo) {
        return UserDTO.builder()
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
