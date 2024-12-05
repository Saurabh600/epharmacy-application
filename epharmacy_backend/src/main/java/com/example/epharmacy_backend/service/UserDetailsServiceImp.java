package com.example.epharmacy_backend.service;

import com.example.epharmacy_backend.entity.UserInfo;
import com.example.epharmacy_backend.entity.UserPrincipal;
import com.example.epharmacy_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Cacheable("users_auth")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByEmailId(username)
                .orElseThrow(() -> new UsernameNotFoundException("invalid emailId " + username));
        return new UserPrincipal(userInfo);
    }
}
