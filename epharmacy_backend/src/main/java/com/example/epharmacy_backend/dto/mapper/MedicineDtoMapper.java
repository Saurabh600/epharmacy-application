package com.example.epharmacy_backend.dto.mapper;

import com.example.epharmacy_backend.dto.response.MedicineDto;
import com.example.epharmacy_backend.entity.Medicine;
import com.example.epharmacy_backend.entity.MedicineCategory;
import com.example.epharmacy_backend.entity.SideEffect;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MedicineDtoMapper implements Function<Medicine, MedicineDto> {
    @Override
    public MedicineDto apply(Medicine medicine) {
        return MedicineDto.builder()
                .id(medicine.getMedicineId())
                .name(medicine.getName())
                .manufacturer(medicine.getManufacturer())
                .expiryDate(medicine.getExpiryDate())
                .prescriptionRequired(medicine.isPrescriptionRequired())
                .usageInstructions(medicine.getUsageInstructions())
                .price(medicine.getPrice())
                .inStock(medicine.isInStock())
                .averageRating(medicine.getRating().getAverage())
                .reviews(medicine.getRating().getReviews())
                .sideEffects(medicine.getSideEffects().stream().map(SideEffect::getName).toList())
                .categories(medicine.getCategories().stream().map(MedicineCategory::getName).toList())
                .stock(medicine.getInventory().getStock())
                .build();
    }
}
