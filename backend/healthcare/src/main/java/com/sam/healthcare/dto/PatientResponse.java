package com.sam.healthcare.dto;

import java.time.LocalDateTime;

public record PatientResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDateTime createdAt
) {
}