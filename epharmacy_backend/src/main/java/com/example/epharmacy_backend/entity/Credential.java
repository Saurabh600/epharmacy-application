package com.example.epharmacy_backend.entity;

import com.example.epharmacy_backend.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_credentials")
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credentialId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
