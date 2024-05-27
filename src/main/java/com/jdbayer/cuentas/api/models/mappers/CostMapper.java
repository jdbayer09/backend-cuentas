package com.jdbayer.cuentas.api.models.mappers;

import com.jdbayer.cuentas.api.models.dto.core.CostDTO;
import com.jdbayer.cuentas.api.models.entities.core.CostEntity;
import com.jdbayer.cuentas.api.models.responses.core.BaseCostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CostMapper {
    BaseCostResponse dtoToBaseResponse(CostDTO dto);
    CostDTO entityToDTO(CostEntity entity);
}
