package com.example.epharmacy_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private final UserInfo userInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(userInfo.getCredential().getRole().toString())
        );
    }

    @Override
    public String getPassword() {
        return userInfo.getCredential().getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getEmailId();
    }
}
