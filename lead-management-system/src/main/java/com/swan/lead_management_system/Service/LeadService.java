package com.swan.lead_management_system.Service;

import com.swan.lead_management_system.dto.LeadRequest;
import com.swan.lead_management_system.dto.LeadResponse;
import com.swan.lead_management_system.Entity.Lead;
import com.swan.lead_management_system.Entity.User;
import com.swan.lead_management_system.Repository.LeadRepository;
import com.swan.lead_management_system.Repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LeadService {

    private final LeadRepository leadRepository;
    private final UserRepository userRepository;

    public LeadService(
            LeadRepository leadRepository,
            UserRepository userRepository) {

        this.leadRepository = leadRepository;
        this.userRepository = userRepository;
    }

    public LeadResponse createLead(LeadRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Lead lead = new Lead();

        lead.setName(request.name());
        lead.setEmail(request.email());
        lead.setPhoneNumber(request.phoneNumber());
        lead.setCompany(request.company());
        lead.setServiceInterested(request.serviceInterested());
        lead.setStatus(request.status());
        lead.setFollowUpDate(request.followUpDate());
        lead.setNotes(request.notes());
        lead.setUser(user);

        Lead savedLead = leadRepository.save(lead);

        return new LeadResponse(
                savedLead.getId(),
                savedLead.getName(),
                savedLead.getEmail(),
                savedLead.getPhoneNumber(),
                savedLead.getCompany(),
                savedLead.getServiceInterested(),
                savedLead.getStatus(),
                savedLead.getFollowUpDate(),
                savedLead.getNotes(),
                savedLead.getCreatedDate()
        );
    }
    public List<LeadResponse> getAllLeads(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Pageable pageable = PageRequest.of(0, 100);

        return leadRepository.findByUserId(user.getId(), pageable)
                .getContent()
                .stream()
                .map(lead -> new LeadResponse(
                        lead.getId(),
                        lead.getName(),
                        lead.getEmail(),
                        lead.getPhoneNumber(),
                        lead.getCompany(),
                        lead.getServiceInterested(),
                        lead.getStatus(),
                        lead.getFollowUpDate(),
                        lead.getNotes(),
                        lead.getCreatedDate()
                ))
                .toList();
    }
    public LeadResponse getLeadById(Long leadId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Lead lead = leadRepository
                .findByIdAndUserId(leadId, user.getId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Lead not found"
                        ));

        return new LeadResponse(
                lead.getId(),
                lead.getName(),
                lead.getEmail(),
                lead.getPhoneNumber(),
                lead.getCompany(),
                lead.getServiceInterested(),
                lead.getStatus(),
                lead.getFollowUpDate(),
                lead.getNotes(),
                lead.getCreatedDate()
        );
    }

    public LeadResponse updateLead(
            Long leadId,
            LeadRequest request,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Lead lead = leadRepository
                .findByIdAndUserId(leadId, user.getId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Lead not found"
                        ));

        lead.setName(request.name());
        lead.setEmail(request.email());
        lead.setPhoneNumber(request.phoneNumber());
        lead.setCompany(request.company());
        lead.setServiceInterested(request.serviceInterested());
        lead.setStatus(request.status());
        lead.setFollowUpDate(request.followUpDate());
        lead.setNotes(request.notes());

        Lead updatedLead = leadRepository.save(lead);

        return new LeadResponse(
                updatedLead.getId(),
                updatedLead.getName(),
                updatedLead.getEmail(),
                updatedLead.getPhoneNumber(),
                updatedLead.getCompany(),
                updatedLead.getServiceInterested(),
                updatedLead.getStatus(),
                updatedLead.getFollowUpDate(),
                updatedLead.getNotes(),
                updatedLead.getCreatedDate()
        );
    }
    public void deleteLead(Long leadId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Lead lead = leadRepository
                .findByIdAndUserId(leadId, user.getId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Lead not found"
                        ));

        leadRepository.delete(lead);
    }

}