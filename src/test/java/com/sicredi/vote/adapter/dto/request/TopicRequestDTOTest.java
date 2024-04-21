package com.sicredi.vote.adapter.dto.request;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TopicRequestDTOTest {
    @Test
    public void testNoArgsConstructor() {
        TopicRequestDTO dto = new TopicRequestDTO();
        assertNull(dto.getDescription());
    }

    @Test
    public void testAllArgsConstructor() {
        TopicRequestDTO dto = new TopicRequestDTO("Discussion on design patterns");
        assertEquals("Discussion on design patterns", dto.getDescription());
    }

    @Test
    public void testBuilder() {
        TopicRequestDTO dto = TopicRequestDTO.builder()
                .description("Discussion on design patterns")
                .build();
        assertEquals("Discussion on design patterns", dto.getDescription());
    }

    @Test
    public void testSettersAndGetters() {
        TopicRequestDTO dto = new TopicRequestDTO();
        dto.setDescription("Discussion on design patterns");
        assertEquals("Discussion on design patterns", dto.getDescription());
    }
}