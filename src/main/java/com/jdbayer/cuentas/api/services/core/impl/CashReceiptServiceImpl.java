package com.jdbayer.cuentas.api.services.core.impl;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CashReceiptDTO;
import com.jdbayer.cuentas.api.models.entities.core.CashReceiptEntity;
import com.jdbayer.cuentas.api.models.mappers.CashReceiptMapper;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.core.CashReceiptRequest;
import com.jdbayer.cuentas.api.repositories.ICashReceiptRepository;
import com.jdbayer.cuentas.api.services.core.ICashReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashReceiptServiceImpl implements ICashReceiptService {

    private final ICashReceiptRepository cashReceiptRepository;
    private final CashReceiptMapper cashReceiptMapper;
    private final UserMapper userMapper;
    private static final String NOT_EXIST_MESSAGE = "No existe el Ingreso";

    @Override
    @Transactional
    public CashReceiptDTO createCashReceipt(UserDTO user, CashReceiptRequest request) {
        var entity = new CashReceiptEntity();
        entity.setPaid(false);
        return createOrUpdateCashReceipt(user, request, entity);
    }

    @Override
    @Transactional
    public CashReceiptDTO updateCashReceipt(UserDTO user, CashReceiptRequest request, Long idCashReceipt) {
        return null;
    }

    private CashReceiptDTO createOrUpdateCashReceipt(UserDTO user, CashReceiptRequest request, CashReceiptEntity entity) {
        entity.setUser(userMapper.dtoToEntity(user));
        entity.setName(request.getName().toUpperCase().trim());
        entity.setColor(request.getColor());
        entity.setMonth(request.getMonth());
        entity.setYear(request.getYear());
        entity.setAmount(request.getAmount());
        return cashReceiptMapper.entityToDto(cashReceiptRepository.save(entity));
    }
}
