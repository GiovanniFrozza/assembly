package com.sicredi.vote.adapter.resource;

import com.sicredi.vote.adapter.dto.request.CreateSessionRequestDTO;
import com.sicredi.vote.adapter.dto.request.TopicRequestDTO;
import com.sicredi.vote.adapter.dto.request.TopicResultRequestDTO;
import com.sicredi.vote.adapter.dto.request.VoteRequestDTO;
import com.sicredi.vote.adapter.dto.response.TopicResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicResultResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicSessionResponseDTO;
import com.sicredi.vote.domain.service.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TopicControllerTest {
    @InjectMocks
    private TopicController topicController;
    @Mock
    private TopicService topicService;

    @Test
    public void testCreateTopic() {
        TopicResponseDTO topicResponseDTO = new TopicResponseDTO();
        topicResponseDTO.setId("1");
        topicResponseDTO.setDescription("New Topic");

        when(topicService.save(any(TopicRequestDTO.class))).thenReturn(topicResponseDTO);

        ResponseEntity<Object> result = topicController.create(new TopicRequestDTO());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void testCreateSession() {
        when(topicService.createSession(any(CreateSessionRequestDTO.class))).thenReturn(new TopicSessionResponseDTO());

        ResponseEntity<Object> result = topicController.createSession(new CreateSessionRequestDTO());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void testFindAllTopics() {
        List<TopicResponseDTO> expectedTopics = Collections.singletonList(new TopicResponseDTO());
        when(topicService.findAll()).thenReturn(expectedTopics);

        ResponseEntity<Object> result = topicController.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testRegisterVote() {
        doNothing().when(topicService).registerVote(any(VoteRequestDTO.class));

        ResponseEntity<Object> result = topicController.registerVote(new VoteRequestDTO());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testResultVote() {
        TopicResultRequestDTO requestDTO = new TopicResultRequestDTO();
        requestDTO.setTopicId("1");

        TopicResultResponseDTO expectedResponse = new TopicResultResponseDTO();
        when(topicService.generateResult(requestDTO.getTopicId())).thenReturn(expectedResponse);

        ResponseEntity<Object> result = topicController.resultVote(requestDTO);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(topicService).generateResult("1");
    }
}