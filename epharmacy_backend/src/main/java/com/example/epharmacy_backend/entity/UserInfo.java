package com.example.epharmacy_backend.entity;

import com.example.epharmacy_backend.util.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_info", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "emailId"})})
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String emailId;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "credential_id")
    private Credential credential;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;
}
