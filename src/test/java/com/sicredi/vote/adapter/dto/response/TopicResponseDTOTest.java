package com.sicredi.vote.adapter.dto.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class TopicResponseDTOTest {
    @Test
    public void testNoArgsConstructor() {
        TopicResponseDTO dto = new TopicResponseDTO();
        assertNull(dto.getId());
        assertNull(dto.getDescription());
        assertNull(dto.getVotes());
    }

    @Test
    public void testAllArgsConstructor() {
        List<VoteResponseDTO> votes = new ArrayList<>();
        votes.add(new VoteResponseDTO());
        TopicResponseDTO dto = new TopicResponseDTO("1", "New Topic", votes);

        assertEquals("1", dto.getId());
        assertEquals("New Topic", dto.getDescription());
        assertNotNull(dto.getVotes());
        assertEquals(1, dto.getVotes().size());
    }

    @Test
    public void testBuilder() {
        VoteResponseDTO vote = new VoteResponseDTO();
        List<VoteResponseDTO> votes = new ArrayList<>();
        votes.add(vote);

        TopicResponseDTO dto = TopicResponseDTO.builder()
                .id("1")
                .description("New Topic")
                .votes(votes)
                .build();

        assertEquals("1", dto.getId());
        assertEquals("New Topic", dto.getDescription());
        assertEquals(1, dto.getVotes().size());
    }

    @Test
    public void testSettersAndGetters() {
        TopicResponseDTO dto = new TopicResponseDTO();
        dto.setId("1");
        dto.setDescription("New Topic");

        List<VoteResponseDTO> votes = new ArrayList<>();
        votes.add(new VoteResponseDTO());
        dto.setVotes(votes);

        assertEquals("1", dto.getId());
        assertEquals("New Topic", dto.getDescription());
        assertEquals(1, dto.getVotes().size());
    }
}