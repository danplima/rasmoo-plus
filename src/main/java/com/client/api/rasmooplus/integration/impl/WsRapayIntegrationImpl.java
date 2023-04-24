package com.client.api.rasmooplus.integration.impl;

import com.client.api.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.api.rasmooplus.dto.wsraspay.OrderDto;
import com.client.api.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.api.rasmooplus.integration.WsRaspayIntegration;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WsRapayIntegrationImpl implements WsRaspayIntegration {

    private RestTemplate restTemplate;

    public WsRapayIntegrationImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        /*HttpEntity<CustomerDto> request = new HttpEntity<>(dto);
        ResponseEntity<CustomerDto> response =
                restTemplate.exchange();*/
        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto dto) {
        return null;
    }

    @Override
    public Boolean processPayment(PaymentDto dto) {
        return null;
    }
}
