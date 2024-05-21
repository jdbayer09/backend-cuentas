package com.jdbayer.cuentas.api.models.responses.core;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Información que se carga en el dashboard sobre los ingresos.")
public record DashboardCashReceiptResponse(
        @Schema(description = "Lista de ingresos pagos")
        List<BaseCashReceiptResponse> cashReceipts,
        @Schema(description = "Total previsto de ingresos.")
        BigDecimal expectedValue,
        @Schema(description = "Total de ingresos ya pago")
        BigDecimal totalPaid
) {
}
