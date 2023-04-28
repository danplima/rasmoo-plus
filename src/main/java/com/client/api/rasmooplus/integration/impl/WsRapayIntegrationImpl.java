package com.client.api.rasmooplus.integration.impl;

import com.client.api.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.api.rasmooplus.dto.wsraspay.OrderDto;
import com.client.api.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.api.rasmooplus.integration.WsRaspayIntegration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WsRapayIntegrationImpl implements WsRaspayIntegration {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public WsRapayIntegrationImpl() {
        restTemplate = new RestTemplate();
        headers = getHttpHeaders();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        try {
            HttpEntity<CustomerDto> request = new HttpEntity<>(dto, this.headers);
            ResponseEntity<CustomerDto> response =
                    restTemplate.exchange("https://ws-raspay.herokuapp.com/ws-raspay/v1/customer", HttpMethod.POST, request, CustomerDto.class);
            System.out.println(response.getBody().getId());
            return response.getBody();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public OrderDto createOrder(OrderDto dto) {
        try {
            HttpEntity<OrderDto> request = new HttpEntity<>(dto, this.headers);
            ResponseEntity<OrderDto> response =
                    restTemplate.exchange("https://ws-raspay.herokuapp.com/ws-raspay/v1/order", HttpMethod.POST, request, OrderDto.class);
            return response.getBody();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean processPayment(PaymentDto dto) {
        return null;
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String credential = "rasmooplus:r@sm00";
        String base64 = new String(Base64.encodeBase64(credential.getBytes()));
        headers.add("Authorization", "Basic "+base64);
        return headers;
    }
}
