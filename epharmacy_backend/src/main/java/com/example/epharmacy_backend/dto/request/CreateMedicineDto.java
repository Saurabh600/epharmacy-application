package com.example.epharmacy_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedicineDto {
    @NotBlank(message = "this field is required")
    private String name;

    @NotBlank(message = "this field is required")
    private String manufacturer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "expiryDate must be in future")
    private Date expiryDate;

    @NotBlank(message = "this field is required")
    private boolean prescriptionRequired;

    @NotBlank(message = "this field is required")
    private String usageInstructions;

    @NotNull(message = "this field is required")
    @Min(value = 1, message = "price can not be less than 1")
    @Max(value = Integer.MAX_VALUE, message = "price can not be higher than INT_MAX")
    private BigDecimal price;

    @NotNull(message = "this field is required")
    @Min(value = 0, message = "inStock can not be negative")
    private boolean inStock;

    @NotNull(message = "this field is required")
    @Min(value = 1, message = "averageRating can not be less than 1")
    @Max(value = 10, message = "averageRating can more than 10")
    private Double averageRating;

    @NotNull(message = "this field is required")
    @Min(value = 0, message = "reviews can not be negative")
    private int reviews;

    @NotNull(message = "this field is required")
    private List<Long> sideEffectIds;

    @NotNull(message = "this field is required")
    private List<Long> categoryIds;

    @NotNull(message = "this field is required")
    private int stock;
}
