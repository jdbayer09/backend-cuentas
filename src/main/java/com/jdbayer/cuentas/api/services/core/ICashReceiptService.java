package com.jdbayer.cuentas.api.services.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CashReceiptDTO;
import com.jdbayer.cuentas.api.models.requests.core.CashReceiptRequest;

import java.util.List;

public interface ICashReceiptService {
    CashReceiptDTO createCashReceipt(UserDTO user, CashReceiptRequest request);
    CashReceiptDTO updateCashReceipt(UserDTO user, CashReceiptRequest request, Long idCashReceipt);
    CashReceiptDTO payCashReceipt(UserDTO user, Long idCashReceipt);
    List<CashReceiptDTO> listAllCashReceipt(UserDTO user, int month, int year);
}
