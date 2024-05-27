package com.jdbayer.cuentas.api.services.core.impl;

import com.jdbayer.cuentas.api.exceptions.core.NotExistCostException;
import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CostDTO;
import com.jdbayer.cuentas.api.models.entities.core.CostEntity;
import com.jdbayer.cuentas.api.models.mappers.CostMapper;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.core.CostRequest;
import com.jdbayer.cuentas.api.repositories.ICostRepository;
import com.jdbayer.cuentas.api.services.core.ICategoryService;
import com.jdbayer.cuentas.api.services.core.ICostService;
import com.jdbayer.cuentas.api.services.core.IPaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostServiceIml implements ICostService {

    private final ICostRepository costRepository;
    private final CostMapper costMapper;
    private final UserMapper userMapper;
    private final ICategoryService categoryService;
    private final IPaymentMethodService paymentMethodService;
    private static final String NOT_EXIST_MESSAGE = "No existe el Gasto";

    @Override
    @Transactional
    public CostDTO createCost(UserDTO user, CostRequest request) {
        var resp = createCostAction(user, request);

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
                createCostAction(user, request);
                x++;
                month++;
            }
        }
        return resp;
    }

    @Override
    @Transactional
    public CostDTO updateCost(UserDTO user, CostRequest request, Long idCashReceipt) {
        var entity = costRepository.findByIdAndUser_Id(idCashReceipt, user.getId()).orElseThrow( () -> new NotExistCostException(NOT_EXIST_MESSAGE));
        return createOrUpdateCost(user, request, entity);
    }

    @Override
    @Transactional
    public CostDTO payCost(UserDTO user, Long idCashReceipt) {
        var entity = costRepository.findByIdAndUser_Id(idCashReceipt, user.getId()).orElseThrow( () -> new NotExistCostException(NOT_EXIST_MESSAGE));
        entity.setPaid(true);
        return costMapper.entityToDTO(costRepository.save(entity));
    }

    @Override
    @Transactional
    public CostDTO deleteCost(UserDTO user, Long idCashReceipt) {
        var entity = costRepository.findByIdAndUser_Id(idCashReceipt, user.getId()).orElseThrow( () -> new NotExistCostException(NOT_EXIST_MESSAGE));
        if (entity.isPaid()) {
            throw new NotExistCostException("No se puede eliminar el gasto");
        }
        costRepository.delete(entity);
        return costMapper.entityToDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostDTO> listAllCosts(UserDTO user, int month, int year) {
        var listCosts = costRepository.fetchAllFilter(user.getId(), month, year);
        return listCosts.stream().map(costMapper::entityToDTO).toList();
    }

    private CostDTO createCostAction(UserDTO user, CostRequest request) {
        var entity = new CostEntity();
        entity.setPaid(false);
        return createOrUpdateCost(user, request, entity);
    }

    private CostDTO createOrUpdateCost(UserDTO user, CostRequest request, CostEntity entity) {
        entity.setUser(userMapper.dtoToEntity(user));
        entity.setName(request.getName().toUpperCase().trim());
        entity.setMonth(request.getMonth());
        entity.setYear(request.getYear());
        entity.setAmount(request.getAmount());
        entity.setCategory(categoryService.getActiveCategoryById(user, request.getCategoryId()));
        entity.setPaymentMethod(paymentMethodService.getActivePaymentMethodById(user, request.getPaymentMethodId()));
        return costMapper.entityToDTO(costRepository.save(entity));
    }
}
