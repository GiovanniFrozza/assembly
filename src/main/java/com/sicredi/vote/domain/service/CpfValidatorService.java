package com.sicredi.vote.domain.service;

import com.sicredi.vote.adapter.dto.response.CpfValidatorResponseDTO;
import com.sicredi.vote.adapter.feign.CpfValidatorClient;
import com.sicredi.vote.infrastructure.exceptions.CpfNotValidException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CpfValidatorService {

    private final CpfValidatorClient client;

    @Value("${cpf.validator.api-token}")
    private String token;
    private final static String TYPE = "cpf";

    public CpfValidatorResponseDTO validateCpf(String cpf){
        var cpfValidated = client.validateCpf(cpf, token, TYPE);

        if(!cpfValidated.isValid()) {
            throw new CpfNotValidException();
        }

        return cpfValidated;
    }

}
