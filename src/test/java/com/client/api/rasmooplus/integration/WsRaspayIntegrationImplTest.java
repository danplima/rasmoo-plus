package com.client.api.rasmooplus.integration;

import com.client.api.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.api.rasmooplus.dto.wsraspay.OrderDto;
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
        CustomerDto customerDto = new CustomerDto(null, "xxxxxxxx","teste@teste","Daniel","Lima");
        wsRaspayIntegration.createCustomer(customerDto);
    }

    @Test
    void createOrderWhenDtoOK() {
        OrderDto orderDto = new OrderDto(null,"xxxxxxx", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDto);
    }

}
