package com.client.api.rasmooplus.service;

import com.client.api.rasmooplus.dto.UserDto;
import com.client.api.rasmooplus.model.User;

public interface UserService {

    User create(UserDto dto);
}
