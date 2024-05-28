package com.jdbayer.cuentas.api.models.responses.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Información que se carga en el dashboard sobre los ingresos.")
public record DashboardCostsByCategoryResponse(
        @Schema(description = "Lista de gastos")
        List<BaseCostResponse> costs,
        @Schema(description = "Categoría de los gastos")
        BaseCategoryResponse category,
        @Schema(description = "Total previsto de gastos.")
        BigDecimal expectedValue,
        @Schema(description = "Total de gastos ya pago")
        BigDecimal totalPaid
) {
}
