package com.jdbayer.cuentas.api.controllers.core;

import com.jdbayer.cuentas.api.models.mappers.CashReceiptMapper;
import com.jdbayer.cuentas.api.models.requests.core.CashReceiptRequest;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
import com.jdbayer.cuentas.api.models.responses.core.BaseCashReceiptResponse;
import com.jdbayer.cuentas.api.models.responses.core.CategoryResponse;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import com.jdbayer.cuentas.api.services.core.ICashReceiptService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cash-receipt")
@RequiredArgsConstructor
@Tag(name = "Controlador de ingresos", description = "Controlador encargado de administrar los ingresos")
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Credentials are required to access this resource", content =
        @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)))
})
@SecurityRequirement(name = "Bearer Authentication")
public class CashReceiptController {

        private final IUserService userService;
        private final CashReceiptMapper mapper;
        private final ICashReceiptService cashReceiptService;

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Creación de Ingreso")
        public ResponseEntity<MessageResponse<BaseCashReceiptResponse>> createCashReceipt(
                Authentication auth,
                @RequestBody @Valid CashReceiptRequest request) {
                var userDto = userService.getUserByEmail(auth.getName());
                var cashReceiptDto = cashReceiptService.createCashReceipt(userDto, request);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El ingreso se ha creado con éxito.",
                                mapper.dtoToBaseResponse(cashReceiptDto)
                        )
                );
        }

        @PutMapping("/update/{idCashReceipt}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Actualización de ingreso.")
        public ResponseEntity<MessageResponse<BaseCashReceiptResponse>> updateCashReceipt(Authentication auth,
            @RequestBody @Valid CashReceiptRequest request, @PathVariable Long idCashReceipt) {
                var userDto = userService.getUserByEmail(auth.getName());
                var cashReceiptDto = cashReceiptService.updateCashReceipt(userDto, request, idCashReceipt);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El ingreso se ha actualizado con éxito.",
                                mapper.dtoToBaseResponse(cashReceiptDto)
                        )
                );
        }

        @PutMapping("/pay/{idCashReceipt}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Función para marcar como pago.")
        public ResponseEntity<MessageResponse<BaseCashReceiptResponse>> payCashReceipt(Authentication auth, @PathVariable Long idCashReceipt) {
                var userDto = userService.getUserByEmail(auth.getName());
                var cashReceiptDto = cashReceiptService.payCashReceipt(userDto, idCashReceipt);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El ingreso se ha marcado como pago.",
                                mapper.dtoToBaseResponse(cashReceiptDto)
                        )
                );
        }

        @GetMapping("/list-all")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Lista todos los ingreso")
        public ResponseEntity<List<BaseCashReceiptResponse>> listAllCashReceipt(
                Authentication auth,
                @RequestParam int month,
                @RequestParam int year
        ) {
                var userDto = userService.getUserByEmail(auth.getName());
                var listCashReceiptDTO = cashReceiptService.listAllCashReceipt(userDto, month, year);
                return ResponseEntity.status(HttpStatus.OK).body(
                        listCashReceiptDTO.stream().map(mapper::dtoToBaseResponse).toList()
                );
        }
}
