package com.jdbayer.cuentas.api.controllers.core;

import com.jdbayer.cuentas.api.models.requests.core.CategoryRequest;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category/{idUser}")
@RequiredArgsConstructor
@Tag(name = "Controlador de categorías", description = "Controlador encargado de administrar las categorías")
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "Credentials are required to access this resource", content =
        @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)))
})
@SecurityRequirement(name = "Bearer Authentication")
public class CategoryController {

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Creación de categoría.")
        public ResponseEntity<MessageResponse<String>> createCategory(
                @RequestBody @Valid CategoryRequest request,
                @PathVariable Long idUser) {
                //var userDto = userService.registerUser(registerUserRequest);

                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "Su categoría se ha creado con éxito.",
                                ""
                        )
                );
        }

        @PutMapping("/update/{idCategory}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Actualización de categoría.")
        public ResponseEntity<MessageResponse<String>> updateCategory(
                @RequestBody @Valid CategoryRequest request,
                @PathVariable Long idUser, @PathVariable Long idCategory) {


                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "Su categoría se ha actualizado con éxito.",
                                ""
                        )
                );
        }

        @DeleteMapping("/delete/{idCategory}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Deshabilita la categoría.")
        public ResponseEntity<MessageResponse<Long>> disableCategory(
                @PathVariable Long idUser, @PathVariable Long idCategory) {

                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "Su categoría se ha deshabilitado con éxito.",
                                idCategory
                        )
                );
        }

        @GetMapping("/list-all")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Lista todas las categorías")
        public ResponseEntity<List<String>> listAllCategory(
                @PathVariable Long idUser) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ArrayList<>()
                );
        }

        @GetMapping("/list-active")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Lista todas las categorías activas")
        public ResponseEntity<List<String>> listActiveCategory(
                @PathVariable Long idUser) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ArrayList<>()
                );
        }

        @PatchMapping("/enable/{idCategory}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Habilita la categoría.")
        public ResponseEntity<MessageResponse<Long>> enableCategory(
                @PathVariable Long idUser, @PathVariable Long idCategory) {

                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "Su categoría se ha habilitado con éxito.",
                                idCategory
                        )
                );
        }

}
