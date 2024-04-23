package com.jdbayer.cuentas.api.models.mappers;

import com.jdbayer.cuentas.api.models.dto.core.PaymentMethodDTO;
import com.jdbayer.cuentas.api.models.entities.core.PaymentMethodEntity;
import com.jdbayer.cuentas.api.models.responses.core.BasePaymentMethodResponse;
import com.jdbayer.cuentas.api.models.responses.core.PaymentMethodResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodMapper {
    BasePaymentMethodResponse dtoToBaseResponse(PaymentMethodDTO dto);
    PaymentMethodResponse dtoToResponse(PaymentMethodDTO dto);
    PaymentMethodDTO entityToDto(PaymentMethodEntity entity);
}
