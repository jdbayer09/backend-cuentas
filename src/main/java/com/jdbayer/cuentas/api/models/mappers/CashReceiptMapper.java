package com.jdbayer.cuentas.api.models.mappers;

import com.jdbayer.cuentas.api.models.dto.core.CashReceiptDTO;
import com.jdbayer.cuentas.api.models.entities.core.CashReceiptEntity;
import com.jdbayer.cuentas.api.models.responses.core.BaseCashReceiptResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CashReceiptMapper {

    BaseCashReceiptResponse dtoToBaseResponse(CashReceiptDTO dto);
    CashReceiptDTO entityToDto(CashReceiptEntity entity);
}
