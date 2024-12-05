package com.example.epharmacy_backend.repository;

import com.example.epharmacy_backend.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmailId(String emailId);
    boolean existsByEmailId(String emailId);
}
