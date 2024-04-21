package com.sicredi.vote.adapter.feign;

import com.sicredi.vote.adapter.dto.response.CpfValidatorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "cpfValidatorClient",
        url = "${cpf.validator.api-url}")
public interface CpfValidatorClient {

    @GetMapping
    CpfValidatorResponseDTO validateCpf(@RequestParam(value = "value") String cpf, @RequestParam(value = "token") String token, @RequestParam(value = "type") String type);
}
