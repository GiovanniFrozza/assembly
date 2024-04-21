package com.sicredi.vote.adapter.mapper;

import com.sicredi.vote.adapter.dto.response.TopicResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicSessionResponseDTO;
import com.sicredi.vote.adapter.dto.response.VoteResponseDTO;
import com.sicredi.vote.domain.model.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class TopicMapperTest {
    @Test
    public void testTopicEntityToResponse() {
        Topic topic = new Topic();
        topic.setId("123");
        topic.setDescription("Discussion about architectural patterns");

        List<VoteResponseDTO> voteResponseDTOs = new ArrayList<>();
        topic.setVotes(new ArrayList<>());

        TopicResponseDTO dto = TopicMapper.topicEntityToResponse(topic);

        assertEquals("123", dto.getId());
        assertEquals("Discussion about architectural patterns", dto.getDescription());
        assertEquals(0, dto.getVotes().size());
    }

    @Test
    public void testTopicSessionEntityToResponse() {
        Topic topic = new Topic();
        topic.setDescription("Discussion about architectural patterns");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusHours(1);

        topic.setSessionStartTime(startTime);
        topic.setSessionEndTime(endTime);

        TopicSessionResponseDTO dto = TopicMapper.topicSessionEntityToResponse(topic);

        assertEquals("Discussion about architectural patterns", dto.getDescription());
        assertEquals(startTime, dto.getSessionStartTime());
        assertEquals(endTime, dto.getSessionEndTime());
    }
}