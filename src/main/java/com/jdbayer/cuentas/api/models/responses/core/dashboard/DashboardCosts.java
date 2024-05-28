package com.jdbayer.cuentas.api.models.responses.core.dashboard;

import java.math.BigDecimal;

public record DashboardCosts(
        BigDecimal expectedValue,
        BigDecimal totalPaid) {
}
