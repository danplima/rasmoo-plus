package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.model.UserType;

import java.util.List;

public interface UserTypeService {

    List<UserType> findAll();

    UserType findById(Long id);
}
