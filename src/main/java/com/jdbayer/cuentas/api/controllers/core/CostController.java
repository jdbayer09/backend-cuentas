package com.jdbayer.cuentas.api.controllers.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CategoryDTO;
import com.jdbayer.cuentas.api.models.dto.core.CostDTO;
import com.jdbayer.cuentas.api.models.dto.core.PaymentMethodDTO;
import com.jdbayer.cuentas.api.models.mappers.CategoryMapper;
import com.jdbayer.cuentas.api.models.mappers.CostMapper;
import com.jdbayer.cuentas.api.models.mappers.PaymentMethodMapper;
import com.jdbayer.cuentas.api.models.requests.core.CostRequest;
import com.jdbayer.cuentas.api.models.responses.base.ErrorResponse;
import com.jdbayer.cuentas.api.models.responses.base.MessageResponse;
import com.jdbayer.cuentas.api.models.responses.core.CostResponse;
import com.jdbayer.cuentas.api.models.responses.core.DashboardCostResponse;
import com.jdbayer.cuentas.api.models.responses.core.DashboardCostsByCategoryResponse;
import com.jdbayer.cuentas.api.models.responses.core.DashboardCostsByPaymentMethodResponse;
import com.jdbayer.cuentas.api.models.responses.core.dashboard.DashboardCosts;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import com.jdbayer.cuentas.api.services.core.ICategoryService;
import com.jdbayer.cuentas.api.services.core.ICostService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        private final ICategoryService categoryService;
        private final CategoryMapper categoryMapper;
        private final IPaymentMethodService paymentMethodService;
        private final PaymentMethodMapper paymentMethodMapper;

        @PostMapping("/create")
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(summary = "Creación de Gasto")
        public ResponseEntity<MessageResponse<CostResponse>> createCost(
                Authentication auth,
                @RequestBody @Valid CostRequest request) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.createCost(userDto, request);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha creado con éxito.",
                                mapper.dtoToResponse(costDto)
                        )
                );
        }

        @PutMapping("/update/{idCost}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Actualización de Gasto")
        public ResponseEntity<MessageResponse<CostResponse>> updateCost(Authentication auth,
                                                                        @RequestBody @Valid CostRequest request, @PathVariable Long idCost) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.updateCost(userDto, request, idCost);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha actualizado con éxito.",
                                mapper.dtoToResponse(costDto)
                        )
                );
        }

        @PutMapping("/pay/{idCost}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Función para marcar como pago.")
        public ResponseEntity<MessageResponse<CostResponse>> payCost(Authentication auth, @PathVariable Long idCost) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.payCost(userDto, idCost);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha marcado como pago.",
                                mapper.dtoToResponse(costDto)
                        )
                );
        }

        @DeleteMapping("/delete/{idCost}")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Función para marcar como pago.")
        public ResponseEntity<MessageResponse<CostResponse>> deleteCost(Authentication auth, @PathVariable Long idCost) {
                var userDto = userService.getUserByEmail(auth.getName());
                var costDto = costService.deleteCost(userDto, idCost);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new MessageResponse<>(
                                "Éxito!",
                                "El gasto se ha eliminado.",
                                mapper.dtoToResponse(costDto)
                        )
                );
        }

        @GetMapping("/list-all")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Lista todos los gastos")
        public ResponseEntity<List<CostResponse>> listAllCosts(
                Authentication auth,
                @RequestParam int month,
                @RequestParam int year
        ) {
                var userDto = userService.getUserByEmail(auth.getName());
                var listCostsDTO = costService.listAllCosts(userDto, month, year);
                return ResponseEntity.status(HttpStatus.OK).body(
                        listCostsDTO.stream().map(mapper::dtoToResponse).toList()
                );
        }

        @GetMapping("/dashboard")
        @ResponseStatus(HttpStatus.OK)
        @Operation(summary = "Método para cargar el dashboard de gastos")
        public ResponseEntity<DashboardCostResponse> dashboardCashReceipt(
                Authentication auth,
                @RequestParam int month,
                @RequestParam int year
        ) {
                var userDto = userService.getUserByEmail(auth.getName());
                var listCostsDTO = costService.listAllCosts(userDto, month, year);

                var allCosts = getDashboardCosts(listCostsDTO);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new DashboardCostResponse(
                                allCosts.expectedValue(),
                                allCosts.totalPaid(),
                                listItemsByCategory(listCostsDTO, userDto),
                                listItemsByPaymentMethod(listCostsDTO, userDto)
                        )
                );
        }

        private List<DashboardCostsByCategoryResponse> listItemsByCategory(List<CostDTO> listCosts, UserDTO userDTO) {
                var listCategories = categoryService.getAllCategories(userDTO);
                List<DashboardCostsByCategoryResponse> itemsByCategory = new ArrayList<>();
                for (CategoryDTO categoryDTO : listCategories) {
                        var listCostsByCategory = listCosts.stream().filter(cost -> cost.getCategory().getId().equals(categoryDTO.getId())).toList();
                        if (!listCostsByCategory.isEmpty()) {
                                var costByCategory = getDashboardCosts(listCostsByCategory);
                                itemsByCategory.add(new DashboardCostsByCategoryResponse(
                                        listCostsByCategory.stream().map(mapper::dtoToBaseResponse).toList(),
                                        categoryMapper.dtoToBaseResponse(categoryDTO),
                                        costByCategory.expectedValue(),
                                        costByCategory.totalPaid()
                                ));
                        }
                }
                return itemsByCategory;
        }
        private List<DashboardCostsByPaymentMethodResponse> listItemsByPaymentMethod(List<CostDTO> listCosts, UserDTO userDTO) {
                var listPaymentMethods = paymentMethodService.getAllPaymentMethods(userDTO);
                List<DashboardCostsByPaymentMethodResponse> itemsByPaymentMethod = new ArrayList<>();

                for (PaymentMethodDTO paymentMethodDTO: listPaymentMethods) {
                        var listCostsByPaymentMethod = listCosts.stream().filter(cost -> cost.getPaymentMethod().getId().equals(paymentMethodDTO.getId())).toList();
                        if (!listCostsByPaymentMethod.isEmpty()) {
                                var costByCategory = getDashboardCosts(listCostsByPaymentMethod);
                                itemsByPaymentMethod.add(new DashboardCostsByPaymentMethodResponse(
                                        listCostsByPaymentMethod.stream().map(mapper::dtoToBaseResponse).toList(),
                                        paymentMethodMapper.dtoToBaseResponse(paymentMethodDTO),
                                        costByCategory.expectedValue(),
                                        costByCategory.totalPaid()
                                ));
                        }
                }
                return itemsByPaymentMethod;
        }

        private DashboardCosts getDashboardCosts(List<CostDTO> listCosts) {
                var totalPaid = new BigDecimal(0);
                var expectedValue = new BigDecimal(0);

                for(CostDTO item : listCosts) {
                        expectedValue = expectedValue.add(item.getAmount());
                        if (item.isPaid()){
                                totalPaid = totalPaid.add(item.getAmount());
                        }
                }
                return new DashboardCosts(expectedValue, totalPaid);
        }
}
