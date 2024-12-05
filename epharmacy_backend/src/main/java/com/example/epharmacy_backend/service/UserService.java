package com.example.epharmacy_backend.service;

import com.example.epharmacy_backend.dto.mapper.UserDtoMapper;
import com.example.epharmacy_backend.dto.request.UserRegisterDto;
import com.example.epharmacy_backend.dto.request.UsernamePasswordDto;
import com.example.epharmacy_backend.dto.response.UserDto;
import com.example.epharmacy_backend.entity.Address;
import com.example.epharmacy_backend.entity.Credential;
import com.example.epharmacy_backend.entity.UserInfo;
import com.example.epharmacy_backend.repository.UserRepository;
import com.example.epharmacy_backend.util.Gender;
import com.example.epharmacy_backend.util.Role;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserById(long userid) {
        UserInfo userInfo = userRepository.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException("invalid userId"));
        return userDtoMapper.apply(userInfo);
    }

    public UserDto getUserByEmailId(String emailId) {
        UserInfo userInfo = userRepository.findByEmailId(emailId.toLowerCase())
                .orElseThrow(() -> new EntityNotFoundException("invalid emailId"));
        return userDtoMapper.apply(userInfo);
    }

    public UserDto createNewUser(UserRegisterDto dto) {
        boolean userExits = userRepository.existsByEmailId(dto.getEmailId().toLowerCase());

        if (userExits) {
            throw new EntityExistsException("user with given emailId already exists!");
        }

        Address address = Address.builder()
                .pinCode(dto.getAddress().getPinCode())
                .street(dto.getAddress().getStreet().toLowerCase())
                .city(dto.getAddress().getCity().toLowerCase())
                .country(dto.getAddress().getCountry().toLowerCase())
                .build();

        Credential credential = Credential.builder()
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.valueOf(dto.getRole().toUpperCase()))
                .build();

        UserInfo userInfo = UserInfo.builder()
                .firstName(dto.getFirstName().toLowerCase())
                .lastName(dto.getLastName().toLowerCase())
                .emailId(dto.getEmailId().toLowerCase())
                .age(dto.getAge())
                .gender(Gender.valueOf(dto.getGender().toUpperCase()))
                .address(address)
                .credential(credential)
                .build();

        UserInfo newUser = userRepository.save(userInfo);

        return userDtoMapper.apply(newUser);
    }

    public void verify(UsernamePasswordDto dto) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmailId().toLowerCase(),
                        dto.getPassword()
                )
        );
    }
}
