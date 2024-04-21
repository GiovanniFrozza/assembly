package com.sicredi.vote.domain.service.mock;

import com.sicredi.vote.adapter.dto.response.VoteValidatorResponseDTO;
import com.sicredi.vote.domain.enumerator.StatusVote;
import com.sicredi.vote.domain.service.VoteValidatorService;
import com.sicredi.vote.infrastructure.exceptions.AssociateUnableToVoteException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@ConditionalOnProperty(
        value = "service.vote.validator.mocked",
        havingValue = "true",
        matchIfMissing = false
)
@Service
@RequiredArgsConstructor
public class VoteValidatorMock implements VoteValidatorService {

    @Override
    public VoteValidatorResponseDTO validateVote(String cpf) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 11);

        if (randomNum > 5) {
            throw new AssociateUnableToVoteException();
        }

        return VoteValidatorResponseDTO.builder().statusVote(StatusVote.ABLE_TO_VOTE).build();
    }
}
