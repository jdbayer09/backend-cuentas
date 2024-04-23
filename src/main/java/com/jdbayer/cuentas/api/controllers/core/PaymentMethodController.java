package com.jdbayer.cuentas.api.controllers.core;

import com.jdbayer.cuentas.api.models.mappers.PaymentMethodMapper;
import com.jdbayer.cuentas.api.models.requests.core.PaymentMethodRequest;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
import com.jdbayer.cuentas.api.models.responses.core.BasePaymentMethodResponse;
import com.jdbayer.cuentas.api.models.responses.core.PaymentMethodResponse;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import com.jdbayer.cuentas.api.services.core.IPaymentMethodService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment-method")
@RequiredArgsConstructor
@Tag(name = "Controlador de métodos de pago", description = "Controlador encargado de administrar ls métodos de pago")
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Credentials are required to access this resource", content =
        @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)))
})
@SecurityRequirement(name = "Bearer Authentication")
public class PaymentMethodController {

    private final PaymentMethodMapper mapper;
    private final IPaymentMethodService paymentMethodService;
    private final IUserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creación del método de pago.")
    public ResponseEntity<MessageResponse<BasePaymentMethodResponse>> createPaymentMethod(
            Authentication auth,
            @RequestBody @Valid PaymentMethodRequest request) {
        var userDto = userService.getUserByEmail(auth.getName());
        var paymentMethodDto = paymentMethodService.createPaymentMethod(userDto, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponse<>(
                        "Éxito!",
                        "El método de pago se ha creado con éxito.",
                        mapper.dtoToBaseResponse(paymentMethodDto)
                )
        );
    }

    @PutMapping("/update/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualización del método de pago.")
    public ResponseEntity<MessageResponse<BasePaymentMethodResponse>> updatePaymentMethod(Authentication auth,
            @RequestBody @Valid PaymentMethodRequest request, @PathVariable Long idPaymentMethod) {
        var userDto = userService.getUserByEmail(auth.getName());
        var paymentMethodDto = paymentMethodService.updatePaymentMethod(userDto, request, idPaymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse<>(
                        "Éxito!",
                        "El método de pago se ha actualizado con éxito.",
                        mapper.dtoToBaseResponse(paymentMethodDto)
                )
        );
    }

    @DeleteMapping("/delete/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deshabilita el método de pago.")
    public ResponseEntity<MessageResponse<Long>> disablePaymentMethod(
            Authentication auth,
            @PathVariable Long idPaymentMethod) {
        var userDto = userService.getUserByEmail(auth.getName());
        paymentMethodService.disablePaymentMethod(userDto, idPaymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse<>(
                        "Éxito!",
                        "El método de pago se ha deshabilitado con éxito.",
                        idPaymentMethod
                )
        );
    }

    @PatchMapping("/enable/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Habilita el método de pago.")
    public ResponseEntity<MessageResponse<Long>> enablePaymentMethod(
            Authentication auth,
            @PathVariable Long idPaymentMethod) {
        var userDto = userService.getUserByEmail(auth.getName());
        paymentMethodService.enablePaymentMethod(userDto, idPaymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse<>(
                        "Éxito!",
                        "El método de pago se ha habilitado con éxito.",
                        idPaymentMethod
                )
        );
    }

    @GetMapping("/list-all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todos los métodos de pago")
    public ResponseEntity<List<PaymentMethodResponse>> listAllPaymentMethod(Authentication auth) {
        var userDto = userService.getUserByEmail(auth.getName());
        var listPaymentMethodDTO = paymentMethodService.getAllPaymentMethods(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                listPaymentMethodDTO.stream().map(mapper::dtoToResponse).toList()
        );
    }

    @GetMapping("/list-active")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todos los métodos de pago activos")
    public ResponseEntity<List<BasePaymentMethodResponse>> listActivePaymentMethod(Authentication auth) {
        var userDto = userService.getUserByEmail(auth.getName());
        var listPaymentMethodDTO = paymentMethodService.getActivePaymentMethods(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(
                listPaymentMethodDTO.stream().map(mapper::dtoToBaseResponse).toList()
        );
    }
}
