package com.jdbayer.cuentas.api.models.responses.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Información que se carga en el dashboard sobre los ingresos.")
public record DashboardCostsByPaymentMethodResponse(
        @Schema(description = "Lista de gastos")
        List<BaseCostResponse> costs,
        @Schema(description = "Método de pago de los gastos")
        BasePaymentMethodResponse paymentMethod,
        @Schema(description = "Total previsto de gastos.")
        BigDecimal expectedValue,
        @Schema(description = "Total de gastos ya pago")
        BigDecimal totalPaid
) {
}
