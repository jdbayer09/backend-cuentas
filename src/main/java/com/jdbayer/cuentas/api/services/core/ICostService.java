package com.jdbayer.cuentas.api.services.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CostDTO;
import com.jdbayer.cuentas.api.models.requests.core.CostRequest;

import java.util.List;

public interface ICostService {
    CostDTO createCost(UserDTO user, CostRequest request);
    CostDTO updateCost(UserDTO user, CostRequest request, Long idCashReceipt);
    CostDTO payCost(UserDTO user, Long idCashReceipt);
    CostDTO deleteCost(UserDTO user, Long idCashReceipt);
    List<CostDTO> listAllCosts(UserDTO user, int month, int year);
}
