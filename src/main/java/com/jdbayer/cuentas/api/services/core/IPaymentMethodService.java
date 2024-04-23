package com.jdbayer.cuentas.api.services.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.PaymentMethodDTO;
import com.jdbayer.cuentas.api.models.requests.core.PaymentMethodRequest;

import java.util.List;

public interface IPaymentMethodService {

    PaymentMethodDTO createPaymentMethod(UserDTO user, PaymentMethodRequest request);
    PaymentMethodDTO updatePaymentMethod(UserDTO user, PaymentMethodRequest request, Long idPaymentMethod);
    void disablePaymentMethod(UserDTO user, Long idPaymentMethod);
    void enablePaymentMethod(UserDTO user, Long idPaymentMethod);
    List<PaymentMethodDTO> getAllPaymentMethods(UserDTO user);
    List<PaymentMethodDTO> getActivePaymentMethods(UserDTO user);
    PaymentMethodDTO getPaymentMethodById(UserDTO user, Long idPaymentMethod);
    PaymentMethodDTO getActivePaymentMethodById(UserDTO user, Long idPaymentMethod);
}
