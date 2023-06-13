package com.client.api.rasmooplus.integration;

import com.client.api.rasmooplus.dto.wsraspay.CreditCardDto;
import com.client.api.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.api.rasmooplus.dto.wsraspay.OrderDto;
import com.client.api.rasmooplus.dto.wsraspay.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;

    @Test
    void createCustomerWhenDtoOk() {
        CustomerDto customerDto = new CustomerDto(null, "65655411083","teste@teste","Daniel","Lima");
        wsRaspayIntegration.createCustomer(customerDto);
    }

    @Test
    void createOrderWhenDtoOK() {
        OrderDto orderDto = new OrderDto(null,"6476868cae80695c0d89d6ac", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDto);
    }

    @Test
    void processPaymentWhenDtoOK() {
        CreditCardDto creditCardDto = new CreditCardDto(123L, "65655411083", 0L, 06L, "1234123412341234", 2025L);
        PaymentDto paymentDto = new PaymentDto(creditCardDto, "6476868cae80695c0d89d6ac", "647687caae80695c0d89d6b0");
        wsRaspayIntegration.processPayment(paymentDto);
    }

}
