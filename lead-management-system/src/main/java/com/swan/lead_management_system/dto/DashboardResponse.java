
package com.swan.lead_management_system.dto;

public record DashboardResponse(
        long totalLeads,
        long newLeads,
        long contactedLeads,
        long qualifiedLeads,
        long convertedLeads,
        long lostLeads
) {
}
