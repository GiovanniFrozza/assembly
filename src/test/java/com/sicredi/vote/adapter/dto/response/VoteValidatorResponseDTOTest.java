package com.sicredi.vote.adapter.dto.response;

import com.sicredi.vote.domain.enumerator.StatusVote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteValidatorResponseDTOTest {
    @Test
    public void testAllArgsConstructor() {
        VoteValidatorResponseDTO dto = new VoteValidatorResponseDTO(StatusVote.ABLE_TO_VOTE);
        assertEquals(StatusVote.ABLE_TO_VOTE, dto.getStatusVote());
    }

    @Test
    public void testBuilder() {
        VoteValidatorResponseDTO dto = VoteValidatorResponseDTO.builder()
                .statusVote(StatusVote.UNABLE_TO_VOTE)
                .build();
        assertEquals(StatusVote.UNABLE_TO_VOTE, dto.getStatusVote());
    }

    @Test
    public void testSettersAndGetters() {
        VoteValidatorResponseDTO dto = new VoteValidatorResponseDTO();
        dto.setStatusVote(StatusVote.ABLE_TO_VOTE);
        assertEquals(StatusVote.ABLE_TO_VOTE, dto.getStatusVote());
    }
}