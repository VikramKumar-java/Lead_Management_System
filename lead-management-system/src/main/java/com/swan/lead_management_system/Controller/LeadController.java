package com.swan.lead_management_system.Controller;

import com.swan.lead_management_system.dto.LeadRequest;
import com.swan.lead_management_system.dto.LeadResponse;
import com.swan.lead_management_system.Service.LeadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    public ResponseEntity<LeadResponse> createLead(
            @Valid @RequestBody LeadRequest request,
            Authentication authentication) {

        String email = authentication.getName();

        LeadResponse response =
                leadService.createLead(request, email);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<LeadResponse>> getAllLeads(
            Authentication authentication) {

        String email = authentication.getName();

        List<LeadResponse> leads =
                leadService.getAllLeads(email);

        return ResponseEntity.ok(leads);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LeadResponse> getLeadById(
            @PathVariable Long id,
            Authentication authentication) {

        LeadResponse response = leadService.getLeadById(
                id,
                authentication.getName()
        );

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LeadResponse> updateLead(
            @PathVariable Long id,
            @Valid @RequestBody LeadRequest request,
            Authentication authentication) {

        LeadResponse response = leadService.updateLead(
                id,
                request,
                authentication.getName()
        );

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(
            @PathVariable Long id,
            Authentication authentication) {

        leadService.deleteLead(id, authentication.getName());

        return ResponseEntity.noContent().build();
    }
}