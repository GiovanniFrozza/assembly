package com.sicredi.vote.adapter.dto.request;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateSessionRequestDTOTest {
    @Test
    public void testNoArgsConstructor() {
        CreateSessionRequestDTO dto = new CreateSessionRequestDTO();

        assertNull(dto.getTopicId());
        assertNotNull(dto.getSessionDurationMinutes());
        assertEquals(Integer.valueOf(1), dto.getSessionDurationMinutes());
    }

    @Test
    public void testAllArgsConstructor() {
        CreateSessionRequestDTO dto = new CreateSessionRequestDTO("6623fa79c243656c3afb2f0c", 10);

        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
        assertEquals(Integer.valueOf(10), dto.getSessionDurationMinutes());
    }

    @Test
    public void testBuilder() {
        CreateSessionRequestDTO dto = CreateSessionRequestDTO.builder()
                .topicId("6623fa79c243656c3afb2f0c")
                .sessionDurationMinutes(10)
                .build();

        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
        assertEquals(Integer.valueOf(10), dto.getSessionDurationMinutes());
    }

    @Test
    public void testSettersAndGetters() {
        CreateSessionRequestDTO dto = new CreateSessionRequestDTO();
        dto.setTopicId("6623fa79c243656c3afb2f0c");
        dto.setSessionDurationMinutes(10);

        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
        assertEquals(Integer.valueOf(10), dto.getSessionDurationMinutes());
    }
}