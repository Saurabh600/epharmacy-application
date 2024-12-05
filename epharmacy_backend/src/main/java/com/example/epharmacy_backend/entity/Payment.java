package com.example.epharmacy_backend.entity;


import com.example.epharmacy_backend.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long orderId;

    @Column(scale = 2, precision = 12)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Status status;
}
