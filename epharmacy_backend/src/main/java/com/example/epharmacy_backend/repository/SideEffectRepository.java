package com.example.epharmacy_backend.repository;

import com.example.epharmacy_backend.entity.SideEffect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SideEffectRepository extends JpaRepository<SideEffect, Long> {
}
