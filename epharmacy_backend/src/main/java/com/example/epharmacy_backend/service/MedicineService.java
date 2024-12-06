package com.example.epharmacy_backend.service;

import com.example.epharmacy_backend.dto.mapper.MedicineDtoMapper;
import com.example.epharmacy_backend.dto.request.CreateMedicineDto;
import com.example.epharmacy_backend.dto.response.MedicineDto;
import com.example.epharmacy_backend.entity.*;
import com.example.epharmacy_backend.repository.MedicineCategoryRepository;
import com.example.epharmacy_backend.repository.MedicineRepository;
import com.example.epharmacy_backend.repository.SideEffectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final MedicineCategoryRepository medicineCategoryRepository;
    private final SideEffectRepository sideEffectRepository;
    private final MedicineDtoMapper medicineDtoMapper;

    public List<MedicineDto> getMedicines() {
        return medicineRepository
                .findAll()
                .stream()
                .map(medicineDtoMapper)
                .toList();
    }

    public MedicineDto getMedicineById(long medicineId) {
        var medicine = medicineRepository.findById(medicineId).orElse(null);
        if (medicine == null) {
            return null;
        }

        return medicineDtoMapper.apply(medicine);
    }

    @Cacheable("medicine_categories")
    public List<MedicineCategory> getMedicineCategories() {
        return medicineCategoryRepository.findAll();
    }

    @Cacheable("side_effects")
    public List<SideEffect> getSideEffects() {
        return sideEffectRepository.findAll();
    }

    public List<MedicineCategory> convertToMedicineCategoryList(List<Long> categoryIds) {
        var medicineCategories = new HashSet<>(getMedicineCategories());

        return categoryIds
                .stream()
                .map(categoryId -> medicineCategories
                        .stream()
                        .filter(category -> category.getCategoryId().equals(categoryId))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("invalid categoryId " + categoryId))
                )
                .toList();
    }

    public List<SideEffect> convertToSideEffectList(List<Long> sideEffectIds) {
        var sideEffects = new HashSet<>(getSideEffects());

        return sideEffectIds
                .stream()
                .map(sideEffectId -> sideEffects
                        .stream()
                        .filter(sideEffect -> sideEffect.getId().equals(sideEffectId))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("invalid sideEffectId " + sideEffectId))
                )
                .toList();
    }

    public MedicineDto createNewMedicine(CreateMedicineDto dto) {
        var ratings = MedicineRating.builder()
                .average(dto.getAverageRating())
                .reviews(dto.getReviews())
                .build();

        var inventory = Inventory.builder()
                .stock(dto.getStock())
                .build();

        var medicine = Medicine.builder()
                .name(dto.getName())
                .manufacturer(dto.getManufacturer())
                .expiryDate(dto.getExpiryDate())
                .prescriptionRequired(dto.isPrescriptionRequired())
                .usageInstructions(dto.getUsageInstructions())
                .price(dto.getPrice())
                .inStock(dto.isInStock())
                .rating(ratings)
                .categories(convertToMedicineCategoryList(dto.getCategoryIds()))
                .sideEffects(convertToSideEffectList(dto.getSideEffectIds()))
                .inventory(inventory)
                .build();

        var newMedicine = medicineRepository.save(medicine);
        return medicineDtoMapper.apply(newMedicine);
    }
}
