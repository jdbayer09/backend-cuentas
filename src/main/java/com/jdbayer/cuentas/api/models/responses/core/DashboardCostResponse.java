package com.jdbayer.cuentas.api.models.responses.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Información que se carga en el dashboard sobre los ingresos.")
public record DashboardCostResponse(
        @Schema(description = "Total previsto de gastos.")
        BigDecimal expectedValue,
        @Schema(description = "Total de gastos ya pago")
        BigDecimal totalPaid,
        @Schema(description = "Lista los gastos por categoría")
        List<DashboardCostsByCategoryResponse> costsByCategory,
        @Schema(description = "Lista los gastos por método de pago")
        List<DashboardCostsByPaymentMethodResponse> costsByPaymentMethod
) {
}
