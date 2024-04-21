package com.sicredi.vote.domain.service.impl;

import com.sicredi.vote.adapter.dto.response.VoteValidatorResponseDTO;
import com.sicredi.vote.adapter.feign.VoteValidatorClient;
import com.sicredi.vote.domain.enumerator.StatusVote;
import com.sicredi.vote.domain.service.VoteValidatorService;
import com.sicredi.vote.infrastructure.exceptions.AssociateUnableToVoteException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
        value = "service.vote.validator.mocked",
        havingValue = "false",
        matchIfMissing = true
)
@Service
@RequiredArgsConstructor
public class VoteValidatorServiceImpl implements VoteValidatorService {
    private final VoteValidatorClient client;

    @Override
    public VoteValidatorResponseDTO validateVote(String cpf) {
        var voteValidated = client.validateVote(cpf);

        if (StatusVote.UNABLE_TO_VOTE.equals(voteValidated.getStatusVote())) {
            throw new AssociateUnableToVoteException();
        }

        return voteValidated;
    }
}
