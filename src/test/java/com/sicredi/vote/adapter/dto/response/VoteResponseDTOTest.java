package com.sicredi.vote.adapter.dto.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteResponseDTOTest {
    @Test
    public void testNoArgsConstructor() {
        VoteResponseDTO dto = new VoteResponseDTO();
        assertNull(dto.getAssociate());
        assertFalse(dto.isAssociateVote());
    }

    @Test
    public void testAllArgsConstructor() {
        AssociateResponseDTO associate = new AssociateResponseDTO("1", "Adalberto Pereira Junior", "12345678901");
        VoteResponseDTO dto = new VoteResponseDTO(associate, true);
        assertEquals(associate, dto.getAssociate());
        assertTrue(dto.isAssociateVote());
    }

    @Test
    public void testBuilder() {
        AssociateResponseDTO associate = new AssociateResponseDTO("1", "Adalberto Pereira Junior", "12345678901");
        VoteResponseDTO dto = VoteResponseDTO.builder()
                .associate(associate)
                .associateVote(true)
                .build();
        assertEquals(associate, dto.getAssociate());
        assertTrue(dto.isAssociateVote());
    }

    @Test
    public void testSettersAndGetters() {
        VoteResponseDTO dto = new VoteResponseDTO();
        AssociateResponseDTO associate = new AssociateResponseDTO("1", "Adalberto Pereira Junior", "12345678901");
        dto.setAssociate(associate);
        dto.setAssociateVote(true);

        assertEquals(associate, dto.getAssociate());
        assertTrue(dto.isAssociateVote());
    }
}