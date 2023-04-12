package com.client.api.rasmooplus.service.impl;

import com.client.api.rasmooplus.dto.SubscriptionTypeDto;
import com.client.api.rasmooplus.exception.BadRequestException;
import com.client.api.rasmooplus.exception.NotFoundException;
import com.client.api.rasmooplus.model.SubscriptionType;
import com.client.api.rasmooplus.repository.SubscriptionTypeRepository;
import com.client.api.rasmooplus.service.SubscriptionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        Optional<SubscriptionType> optionalSubscriptionType = subscriptionTypeRepository.findById(id);

        if (optionalSubscriptionType.isEmpty()) {
            throw new NotFoundException("SubscriptionType não encontrado");
        }

        return optionalSubscriptionType.get();
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto dto) {
        if (Objects.nonNull(dto.getId())) {
            throw new BadRequestException("Id deve ser Nulo");
        }
        return subscriptionTypeRepository.save(SubscriptionType.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .accessMonth(dto.getAccessMonth())
                        .price(dto.getPrice())
                        .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionType subscriptionType) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
