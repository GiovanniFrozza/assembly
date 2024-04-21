package com.sicredi.vote.adapter.mapper;

import com.sicredi.vote.adapter.dto.response.VoteResponseDTO;
import com.sicredi.vote.domain.model.Associate;
import com.sicredi.vote.domain.model.Vote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteMapperTest {
    @Test
    public void testVoteEntityToResponse() {
        Associate associate = new Associate();
        associate.setId("1");
        associate.setName("John Doe");
        associate.setCpf("1234567890");

        Vote vote = new Vote();
        vote.setAssociate(associate);
        vote.setAssociateVote(true);

        VoteResponseDTO dto = VoteMapper.voteEntityToResponse(vote);

        assertEquals(dto.getAssociate().getId(), "1");
        assertEquals(dto.getAssociate().getName(), "John Doe");
        assertEquals(dto.getAssociate().getCpf(), "1234567890");
        assertTrue(dto.isAssociateVote());
    }
}