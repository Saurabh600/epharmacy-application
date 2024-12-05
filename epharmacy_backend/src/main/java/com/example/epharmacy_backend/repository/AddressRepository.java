package com.example.epharmacy_backend.repository;

import com.example.epharmacy_backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
