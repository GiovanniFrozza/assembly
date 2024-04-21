package com.sicredi.vote.adapter.dto.request;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VoteRequestDTOTest {
    @Test
    public void testNoArgsConstructor() {
        VoteRequestDTO dto = new VoteRequestDTO();
        assertNull(dto.getTopicId());
        assertNull(dto.getAssociateId());
        assertFalse(dto.isVote());
    }

    @Test
    public void testAllArgsConstructor() {
        VoteRequestDTO dto = new VoteRequestDTO("6623fa79c243656c3afb2f0c", "6623131093943364f448e32e", true);
        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
        assertEquals("6623131093943364f448e32e", dto.getAssociateId());
        assertTrue(dto.isVote());
    }

    @Test
    public void testBuilder() {
        VoteRequestDTO dto = VoteRequestDTO.builder()
                .topicId("6623fa79c243656c3afb2f0c")
                .associateId("6623131093943364f448e32e")
                .vote(true)
                .build();
        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
        assertEquals("6623131093943364f448e32e", dto.getAssociateId());
        assertTrue(dto.isVote());
    }

    @Test
    public void testSettersAndGetters() {
        VoteRequestDTO dto = new VoteRequestDTO();
        dto.setTopicId("6623fa79c243656c3afb2f0c");
        dto.setAssociateId("6623131093943364f448e32e");
        dto.setVote(true);

        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
        assertEquals("6623131093943364f448e32e", dto.getAssociateId());
        assertTrue(dto.isVote());
    }
}