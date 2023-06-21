package com.client.api.rasmooplus.service.impl;

import com.client.api.rasmooplus.dto.PaymentProcessDto;
import com.client.api.rasmooplus.dto.wsraspay.CreditCardDto;
import com.client.api.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.api.rasmooplus.dto.wsraspay.OrderDto;
import com.client.api.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.api.rasmooplus.exception.BusinessException;
import com.client.api.rasmooplus.exception.NotFoundException;
import com.client.api.rasmooplus.integration.MailIntegration;
import com.client.api.rasmooplus.integration.WsRaspayIntegration;
import com.client.api.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.api.rasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.api.rasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.api.rasmooplus.mapper.wsraspay.OrderMapper;
import com.client.api.rasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.api.rasmooplus.model.User;
import com.client.api.rasmooplus.model.UserPaymentInfo;
import com.client.api.rasmooplus.repository.UserPaymentInfoRepository;
import com.client.api.rasmooplus.repository.UserRepository;
import com.client.api.rasmooplus.service.PaymentInfoService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    private final UserRepository userRepository;
    private final UserPaymentInfoRepository userPaymentInfoRepository;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final MailIntegration mailIntegration;

    public PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository, WsRaspayIntegration wsRaspayIntegration, MailIntegration mailIntegration) {
        this.userRepository = userRepository;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.mailIntegration = mailIntegration;
    }

    @Override
    public Boolean process(PaymentProcessDto dto) {
        //Verificar usuario por id e verifica se já existe assinatura
        var userOptional = userRepository.findById(dto.getUserPaymentInfoDto().getUserId());

        if(userOptional.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        User user = userOptional.get();
        if (Objects.nonNull(user.getSubscriptionType())) {
            throw new BusinessException("Pagamento não pode ser processado pois usuário já possui assinatura");
        }

        //cria ou atualiazar usuario raspay
        CustomerDto customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.build(user));
        //cria o pedido de pagamento
        OrderDto orderDto = wsRaspayIntegration.createOrder(OrderMapper.build(customerDto.getId(), dto));

        //processa o pagamento
        PaymentDto paymentDto = PaymentMapper.build(customerDto.getId(), orderDto.getId(), CreditCardMapper.build(dto.getUserPaymentInfoDto(), user.getCpf()));
        Boolean successPayment = wsRaspayIntegration.processPayment(paymentDto);

        if (successPayment) {
            //Salvar informações de pagamento
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);
            //enviar email de criação de conta.
            mailIntegration.send(user.getEmail(),"Seja bem-vindo, Usuario:"+user.getEmail()+" - Senha: alunorasmoo", "Acesso Liberado");
            return true;
        }

        //Retorna o sucesso ou não do pagamento
        return false;
    }
}
