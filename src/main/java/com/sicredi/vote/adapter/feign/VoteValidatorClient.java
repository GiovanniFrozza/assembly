package com.sicredi.vote.adapter.feign;

import com.sicredi.vote.adapter.dto.response.CpfValidatorResponseDTO;
import com.sicredi.vote.adapter.dto.response.VoteValidatorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "voteValidatorClient",
        url = "${vote.validator.api-url}")
public interface VoteValidatorClient {

    @GetMapping("/{cpf}")
    VoteValidatorResponseDTO validateVote(@PathVariable(value = "cpf") String cpf);
}
