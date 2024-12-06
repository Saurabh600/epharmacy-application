package com.example.epharmacy_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {
    private Long id;

    private String name;

    private String manufacturer;

    private Date expiryDate;

    private boolean prescriptionRequired;

    private String usageInstructions;

    private BigDecimal price;

    private boolean inStock;

    private Double averageRating;

    private int reviews;

    private List<String> sideEffects;

    private List<String> categories;

    private int stock;
}
