package com.jdbayer.cuentas.api.controllers.core;

import com.jdbayer.cuentas.api.models.dto.core.CashReceiptDTO;
import com.jdbayer.cuentas.api.models.mappers.CostMapper;
import com.jdbayer.cuentas.api.models.requests.core.CashReceiptRequest;
import com.jdbayer.cuentas.api.models.requests.core.CostRequest;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
import com.jdbayer.cuentas.api.models.responses.core.BaseCashReceiptResponse;
import com.jdbayer.cuentas.api.models.responses.core.BaseCostResponse;
import com.jdbayer.cuentas.api.models.responses.core.DashboardCashReceiptResponse;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import com.jdbayer.cuentas.api.services.core.ICostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cost")
@RequiredArgsConstructor
@Tag(name = "Controlador de Gastos", description = "Controlador encargado de administrar llos gastos")
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Credentials are required to access this resource", content =
        @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)))
})
@SecurityRequirement(name = "Bearer Authentication")
public class CostController {
        private final IUserService userService;
        private final CostMapper mapper;
        private final ICostService costService;

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Creación de Gasto")
        public ResponseEntity<MessageResponse<BaseCostResponse>> createCost(
                Authentication auth,
                @RequestBody @Valid CostRequest request) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.createCost(userDto, request);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha creado con éxito.",
                                mapper.dtoToBaseResponse(costDto)
                        )
                );
        }

        @PutMapping("/update/{idCost}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Actualización de Gasto")
        public ResponseEntity<MessageResponse<BaseCostResponse>> updateCost(Authentication auth,
          @RequestBody @Valid CostRequest request, @PathVariable Long idCost) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.updateCost(userDto, request, idCost);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha actualizado con éxito.",
                                mapper.dtoToBaseResponse(costDto)
                        )
                );
        }

        @PutMapping("/pay/{idCost}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Función para marcar como pago.")
        public ResponseEntity<MessageResponse<BaseCostResponse>> payCost(Authentication auth, @PathVariable Long idCost) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.payCost(userDto, idCost);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha marcado como pago.",
                                mapper.dtoToBaseResponse(costDto)
                        )
                );
        }

        @DeleteMapping("/delete/{idCost}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Función para marcar como pago.")
        public ResponseEntity<MessageResponse<BaseCostResponse>> deleteCost(Authentication auth, @PathVariable Long idCost) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.deleteCost(userDto, idCost);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha eliminado.",
                                mapper.dtoToBaseResponse(costDto)
                        )
                );
        }

        @GetMapping("/list-all")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Lista todos los gastos")
        public ResponseEntity<List<BaseCostResponse>> listAllCosts(
                Authentication auth,
                @RequestParam int month,
                @RequestParam int year
        ) {
                var userDto = userService.getUserByEmail(auth.getName());
                var listCostsDTO = costService.listAllCosts(userDto, month, year);
                return ResponseEntity.status(HttpStatus.OK).body(
                        listCostsDTO.stream().map(mapper::dtoToBaseResponse).toList()
                );
        }

        @GetMapping("/dashboard")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Metodo para cargar el dashboard de ingresos")
        public ResponseEntity<Objects> dashboardCashReceipt(
                Authentication auth,
                @RequestParam int month,
                @RequestParam int year
        ) {
                return null;
                /*
                var userDto = userService.getUserByEmail(auth.getName());
                var listAllCashReceipt = cashReceiptService.listAllCashReceipt(userDto, month, year);

                double expectedValue = 0;
                double totalPaid = 0;
                for(CashReceiptDTO item : listAllCashReceipt) {
                        expectedValue += item.getAmount().doubleValue();
                        if (item.isPaid()){
                                totalPaid += item.getAmount().doubleValue();
                        }
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DashboardCashReceiptResponse(
                                listAllCashReceipt.stream().map(mapper::dtoToBaseResponse).toList(),
                                BigDecimal.valueOf(expectedValue),
                                BigDecimal.valueOf(totalPaid)
                        )
                ); */
        }
}
