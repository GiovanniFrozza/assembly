package com.sicredi.vote.domain.service;

import com.sicredi.vote.adapter.dto.response.VoteValidatorResponseDTO;

public interface VoteValidatorService {

    VoteValidatorResponseDTO validateVote(String cpf);
}
