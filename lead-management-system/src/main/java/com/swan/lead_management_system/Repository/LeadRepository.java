package com.swan.lead_management_system.Repository;

import com.swan.lead_management_system.Entity.Lead;
import com.swan.lead_management_system.Entity.LeadStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long>, JpaSpecificationExecutor<Lead> {

    Page<Lead> findByUserId(Long userId, Pageable pageable);

    Optional<Lead> findByIdAndUserId(Long leadId, Long userId);

    long countByUserId(Long userId);


    long countByUserIdAndStatus(Long userId, LeadStatus status);
    List<Lead> findByUserId(Long userId);

}