package com.client.api.rasmooplus.mapper;

import com.client.api.rasmooplus.dto.UserPaymentInfoDto;
import com.client.api.rasmooplus.model.User;
import com.client.api.rasmooplus.model.UserPaymentInfo;

public class UserPaymentInfoMapper {

    public static UserPaymentInfo fromDtoToEntity(UserPaymentInfoDto dto, User user) {
        return UserPaymentInfo.builder()
                .id(dto.getId())
                .cardNumber(dto.getCardNumber())
                .cardExpirationMonth(dto.getCardExpirationMonth())
                .cardSecurityCode(dto.getCardSecurityCode())
                .price(dto.getPrice())
                .dtPayment(dto.getDtPayment())
                .user(user)
                .build();
    }

}
