package com.swan.lead_management_system.dto;

import com.swan.lead_management_system.Entity.LeadStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LeadResponse(
        Long id,

        String name,

        String email,

        String phoneNumber,

        String company,

        String serviceInterested,

        LeadStatus status,

        LocalDate followUpDate,

        String notes,

        LocalDateTime createdDate
) {
}
