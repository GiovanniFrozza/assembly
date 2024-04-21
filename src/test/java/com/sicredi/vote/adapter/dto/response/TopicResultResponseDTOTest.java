package com.sicredi.vote.adapter.dto.response;

import com.sicredi.vote.domain.enumerator.ResultType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class TopicResultResponseDTOTest {
    @Test
    public void testNoArgsConstructor() {
        TopicResultResponseDTO dto = new TopicResultResponseDTO();
        assertEquals(0, dto.getTotalVotes());
        assertEquals(0, dto.getYesVotes());
        assertEquals(0, dto.getNoVotes());
        assertNull(dto.getMostVoted());
    }

    @Test
    public void testAllArgsConstructor() {
        TopicResultResponseDTO dto = new TopicResultResponseDTO(100, 60, 40, ResultType.YES);
        assertEquals(100, dto.getTotalVotes());
        assertEquals(60, dto.getYesVotes());
        assertEquals(40, dto.getNoVotes());
        assertEquals(ResultType.YES, dto.getMostVoted());
    }

    @Test
    public void testBuilder() {
        TopicResultResponseDTO dto = TopicResultResponseDTO.builder()
                .totalVotes(100)
                .yesVotes(60)
                .noVotes(40)
                .mostVoted(ResultType.YES)
                .build();
        assertEquals(100, dto.getTotalVotes());
        assertEquals(60, dto.getYesVotes());
        assertEquals(40, dto.getNoVotes());
        assertEquals(ResultType.YES, dto.getMostVoted());
    }

    @Test
    public void testSettersAndGetters() {
        TopicResultResponseDTO dto = new TopicResultResponseDTO();
        dto.setTotalVotes(100);
        dto.setYesVotes(60);
        dto.setNoVotes(40);
        dto.setMostVoted(ResultType.YES);

        assertEquals(100, dto.getTotalVotes());
        assertEquals(60, dto.getYesVotes());
        assertEquals(40, dto.getNoVotes());

    }
}
