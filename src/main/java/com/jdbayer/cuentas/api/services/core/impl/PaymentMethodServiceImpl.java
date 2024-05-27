package com.jdbayer.cuentas.api.services.core.impl;

import com.jdbayer.cuentas.api.exceptions.core.NotExistPaymentMethodException;
import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.PaymentMethodDTO;
import com.jdbayer.cuentas.api.models.entities.core.PaymentMethodEntity;
import com.jdbayer.cuentas.api.models.mappers.PaymentMethodMapper;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.core.PaymentMethodRequest;
import com.jdbayer.cuentas.api.repositories.IPaymentMethodRepository;
import com.jdbayer.cuentas.api.services.core.IPaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements IPaymentMethodService {

    private final IPaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper mapper;
    private final UserMapper userMapper;
    private static final String NOT_EXIST_MESSAGE = "No existe el método de pago";

    @Override
    @Transactional
    public PaymentMethodDTO createPaymentMethod(UserDTO user, PaymentMethodRequest request) {
        var entity = new PaymentMethodEntity();
        entity.setActive(true);
        return createOrUpdatePaymentMethod(entity, request, user);
    }

    @Override
    @Transactional
    public PaymentMethodDTO updatePaymentMethod(UserDTO user, PaymentMethodRequest request, Long idPaymentMethod) {
        var entity = paymentMethodRepository.findByIdAndUser_Id(idPaymentMethod, user.getId()).orElseThrow(() -> new NotExistPaymentMethodException(NOT_EXIST_MESSAGE));
        return createOrUpdatePaymentMethod(entity, request, user);
    }

    @Override
    @Transactional
    public void disablePaymentMethod(UserDTO user, Long idPaymentMethod) {
        var entity = paymentMethodRepository.findByIdAndUser_Id(idPaymentMethod, user.getId()).orElseThrow(() -> new NotExistPaymentMethodException(NOT_EXIST_MESSAGE));
        entity.setActive(false);
        paymentMethodRepository.save(entity);
    }

    @Override
    @Transactional
    public void enablePaymentMethod(UserDTO user, Long idPaymentMethod) {
        var entity = paymentMethodRepository.findByIdAndUser_Id(idPaymentMethod, user.getId()).orElseThrow(() -> new NotExistPaymentMethodException(NOT_EXIST_MESSAGE));
        entity.setActive(true);
        paymentMethodRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentMethodDTO> getAllPaymentMethods(UserDTO user) {
        List<PaymentMethodEntity> paymentMethods = paymentMethodRepository.findAllByUser_IdOrderByName(user.getId());
        return paymentMethods.stream().map(mapper::entityToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentMethodDTO> getActivePaymentMethods(UserDTO user) {
        List<PaymentMethodEntity> paymentMethods = paymentMethodRepository.findAllByActiveIsTrueAndUser_IdOrderByName(user.getId());
        if (paymentMethods.isEmpty()) {
            throw new NotExistPaymentMethodException("No hay métodos de pago disponibles");
        }
        return paymentMethods.stream().map(mapper::entityToDto).toList();
    }



    @Override
    @Transactional(readOnly = true)
    public PaymentMethodEntity getActivePaymentMethodById(UserDTO user, Long idPaymentMethod) {
        var entity = paymentMethodRepository.findByIdAndUser_Id(idPaymentMethod, user.getId()).orElseThrow(() -> new NotExistPaymentMethodException(NOT_EXIST_MESSAGE));
        if (!entity.isActive()) {
            throw new NotExistPaymentMethodException("El método de pago no esta activo");
        }
        return entity;
    }

    private PaymentMethodDTO createOrUpdatePaymentMethod(PaymentMethodEntity paymentMethod, PaymentMethodRequest request, UserDTO user) {
        paymentMethod.setUser(userMapper.dtoToEntity(user));
        paymentMethod.setName(request.getName().toUpperCase().trim());
        paymentMethod.setDescription(request.getDescription());
        paymentMethod.setIcon(request.getIcon().toLowerCase().trim());
        paymentMethod.setColor(request.getColor());
        paymentMethod.setPaymentDate(request.getPaymentDate());
        return mapper.entityToDto(paymentMethodRepository.save(paymentMethod));
    }
}
