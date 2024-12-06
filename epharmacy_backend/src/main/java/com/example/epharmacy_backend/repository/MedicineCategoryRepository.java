package com.example.epharmacy_backend.repository;

import com.example.epharmacy_backend.entity.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, Long> {
}
