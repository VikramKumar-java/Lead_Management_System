package com.swan.lead_management_system.dto;

import com.swan.lead_management_system.Entity.LeadStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LeadRequest(
        @NotBlank(message = "Lead name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email,

        String phoneNumber,

        String company,

        String serviceInterested,

        @NotNull(message = "Lead status is required")
        LeadStatus status,

        LocalDate followUpDate,

        @Size(max = 2000, message = "Maximum 2000 characters")
        String notes
) {
}
