package com.client.api.rasmooplus.service.impl;

import com.client.api.rasmooplus.exception.NotFoundException;
import com.client.api.rasmooplus.model.UserType;
import com.client.api.rasmooplus.repository.UserTypeRepository;
import com.client.api.rasmooplus.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserType findById(Long id) {
        return getUserType(id);
    }

    private UserType getUserType(Long id) {
        Optional<UserType> optionalUserType = userTypeRepository.findById(id);

        if (optionalUserType.isEmpty()) {
            throw new NotFoundException("userType não encontrado");
        }
        return optionalUserType.get();
    }
}
