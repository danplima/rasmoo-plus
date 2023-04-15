package com.client.api.rasmooplus.mapper;

import com.client.api.rasmooplus.dto.UserDto;
import com.client.api.rasmooplus.model.SubscriptionType;
import com.client.api.rasmooplus.model.User;
import com.client.api.rasmooplus.model.UserType;

public class UserMapper {

    public static User fromDtoToEntity(UserDto dto, UserType userType, SubscriptionType subscriptionType) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .dtSubscription(dto.getDtSubscription())
                .dtExpiration(dto.getDtExpiration())
                .userType(userType)
                .subscriptionType(subscriptionType)
                .build();
    }
}
