package com.sicredi.vote.adapter.dto.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class TopicSessionResponseDTOTest {
    @Test
    public void testNoArgsConstructor() {
        TopicSessionResponseDTO dto = new TopicSessionResponseDTO();
        assertNull(dto.getDescription());
        assertNull(dto.getSessionStartTime());
        assertNull(dto.getSessionEndTime());
    }

    @Test
    public void testAllArgsConstructor() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        TopicSessionResponseDTO dto = new TopicSessionResponseDTO("Discussion on design patterns", startTime, endTime);
        assertEquals("Discussion on design patterns", dto.getDescription());
        assertEquals(startTime, dto.getSessionStartTime());
        assertEquals(endTime, dto.getSessionEndTime());
    }

    @Test
    public void testBuilder() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        TopicSessionResponseDTO dto = TopicSessionResponseDTO.builder()
                .description("Discussion on design patterns")
                .sessionStartTime(startTime)
                .sessionEndTime(endTime)
                .build();
        assertEquals("Discussion on design patterns", dto.getDescription());
        assertEquals(startTime, dto.getSessionStartTime());
        assertEquals(endTime, dto.getSessionEndTime());
    }

    @Test
    public void testSettersAndGetters() {
        TopicSessionResponseDTO dto = new TopicSessionResponseDTO();
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        dto.setDescription("Discussion on design patterns");
        dto.setSessionStartTime(startTime);
        dto.setSessionEndTime(endTime);

        assertEquals("Discussion on design patterns", dto.getDescription());
        assertEquals(startTime, dto.getSessionStartTime());
        assertEquals(endTime, dto.getSessionEndTime());
    }
}