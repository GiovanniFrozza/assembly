package com.sicredi.vote.adapter.dto.request;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class TopicResultRequestDTOTest {

    @Test
    public void testNoArgsConstructor() {
        TopicResultRequestDTO dto = new TopicResultRequestDTO();
        assertNull(dto.getTopicId());
    }

    @Test
    public void testAllArgsConstructor() {
        TopicResultRequestDTO dto = new TopicResultRequestDTO("6623fa79c243656c3afb2f0c");
        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
    }

    @Test
    public void testBuilder() {
        TopicResultRequestDTO dto = TopicResultRequestDTO.builder()
                .topicId("6623fa79c243656c3afb2f0c")
                .build();
        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
    }

    @Test
    public void testSettersAndGetters() {
        TopicResultRequestDTO dto = new TopicResultRequestDTO();
        dto.setTopicId("6623fa79c243656c3afb2f0c");
        assertEquals("6623fa79c243656c3afb2f0c", dto.getTopicId());
    }
}