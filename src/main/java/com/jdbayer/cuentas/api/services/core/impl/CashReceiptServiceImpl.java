package com.jdbayer.cuentas.api.services.core.impl;

import com.jdbayer.cuentas.api.exceptions.core.NotExistCashReceiptException;
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

import java.util.List;

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
        var resp = createCashReceiptAction(user, request);

        if (Boolean.TRUE.equals(request.getReplicate())) {
            int x = 1;
            int month = request.getMonth() + 1;
            int year = request.getYear();

            while (x <= request.getReplicateVal()) {
                if (month == 13) {
                    month = 1;
                    year++;
                }
                request.setYear(year);
                request.setMonth(month);
                createCashReceiptAction(user, request);
                x++;
                month++;
            }
        }
        return resp;
    }

    private CashReceiptDTO createCashReceiptAction(UserDTO user, CashReceiptRequest request) {
        var entity = new CashReceiptEntity();
        entity.setPaid(false);
        return createOrUpdateCashReceipt(user, request, entity);
    }

    @Override
    @Transactional
    public CashReceiptDTO updateCashReceipt(UserDTO user, CashReceiptRequest request, Long idCashReceipt) {
        var entity = cashReceiptRepository.findByIdAndUser_Id(idCashReceipt, user.getId()).orElseThrow( () -> new NotExistCashReceiptException(NOT_EXIST_MESSAGE));
        return createOrUpdateCashReceipt(user, request, entity);
    }

    @Override
    @Transactional
    public CashReceiptDTO payCashReceipt(UserDTO user, Long idCashReceipt) {
        var entity = cashReceiptRepository.findByIdAndUser_Id(idCashReceipt, user.getId()).orElseThrow( () -> new NotExistCashReceiptException(NOT_EXIST_MESSAGE));
        entity.setPaid(true);
        return cashReceiptMapper.entityToDto(cashReceiptRepository.save(entity));
    }

    @Override
    @Transactional
    public CashReceiptDTO deleteCashReceipt(UserDTO user, Long idCashReceipt) {
        var entity = cashReceiptRepository.findByIdAndUser_Id(idCashReceipt, user.getId()).orElseThrow( () -> new NotExistCashReceiptException(NOT_EXIST_MESSAGE));
        if (entity.isPaid()) {
            throw new NotExistCashReceiptException("No se puede eliminar el ingreso");
        }
        cashReceiptRepository.delete(entity);
        return cashReceiptMapper.entityToDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CashReceiptDTO> listAllCashReceipt(UserDTO user, int month, int year) {
        var listCashReceipt = cashReceiptRepository.fetchAllFilter(user.getId(), month, year);
        return listCashReceipt.stream().map(cashReceiptMapper::entityToDto).toList();
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
