
package com.swan.lead_management_system.Service;

import com.swan.lead_management_system.Entity.LeadStatus;
import com.swan.lead_management_system.Entity.User;
import com.swan.lead_management_system.Repository.LeadRepository;
import com.swan.lead_management_system.Repository.UserRepository;
import com.swan.lead_management_system.dto.DashboardResponse;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final LeadRepository leadRepository;
    private final UserRepository userRepository;

    public DashboardService(
            LeadRepository leadRepository,
            UserRepository userRepository) {

        this.leadRepository = leadRepository;
        this.userRepository = userRepository;
    }

    public DashboardResponse getDashboard(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        Long userId = user.getId();

        long total = leadRepository.countByUserId(userId);

        long newLeads = leadRepository.countByUserIdAndStatus(
                userId, LeadStatus.NEW);

        long contacted = leadRepository.countByUserIdAndStatus(
                userId, LeadStatus.CONTACTED);

        long qualified = leadRepository.countByUserIdAndStatus(
                userId, LeadStatus.QUALIFIED);

        long converted = leadRepository.countByUserIdAndStatus(
                userId, LeadStatus.CONVERTED);

        long lost = leadRepository.countByUserIdAndStatus(
                userId, LeadStatus.LOST);

        return new DashboardResponse(
                total,
                newLeads,
                contacted,
                qualified,
                converted,
                lost
        );
    }
}
