package com.sicredi.vote.domain.service;

import com.sicredi.vote.adapter.dto.request.CreateSessionRequestDTO;
import com.sicredi.vote.adapter.dto.request.TopicRequestDTO;
import com.sicredi.vote.adapter.dto.request.VoteRequestDTO;
import com.sicredi.vote.adapter.dto.response.TopicResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicSessionResponseDTO;
import com.sicredi.vote.domain.model.Associate;
import com.sicredi.vote.domain.model.Topic;
import com.sicredi.vote.domain.model.Vote;
import com.sicredi.vote.domain.repository.AssociateRepository;
import com.sicredi.vote.domain.repository.TopicRepository;
import com.sicredi.vote.infrastructure.exceptions.SessionNotAvailableException;
import com.sicredi.vote.infrastructure.exceptions.SessionNotValidForVoteException;
import com.sicredi.vote.infrastructure.exceptions.TopicNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTest {
    @InjectMocks
    private TopicService topicService;
    @Mock
    private TopicRepository topicRepository;
    @Mock
    private AssociateRepository associateRepository;
    @Mock
    private AssociateService associateService;
    @Mock
    private VoteValidatorService voteValidatorService;

    @Test
    public void testCreateTopic() {
        TopicRequestDTO topicRequestDTO = new TopicRequestDTO();
        topicRequestDTO.setDescription("Discussion on design patterns");

        Topic mockTopic = new Topic();
        mockTopic.setDescription(topicRequestDTO.getDescription());

        when(topicRepository.save(Mockito.any(Topic.class))).thenReturn(mockTopic);

        TopicResponseDTO topicCreated = topicService.save(topicRequestDTO);

        assertEquals(topicRequestDTO.getDescription(), topicCreated.getDescription());
    }

    @Test
    public void testFindByIdWhenFound() {
        String id = "1";
        Topic expectedTopic = new Topic();
        expectedTopic.setId(id);
        when(topicRepository.findById(id)).thenReturn(Optional.of(expectedTopic));

        Topic result = topicService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test(expected = TopicNotFoundException.class)
    public void testFindByIdWhenNotFound() {
        String id = "1";
        when(topicRepository.findById(id)).thenReturn(Optional.empty());

        topicService.findById(id);
    }

    @Test
    public void testFindAll() {
        Topic topic1 = new Topic();
        topic1.setId("1");
        topic1.setDescription("Discussion about architectural patterns");

        Topic topic2 = new Topic();
        topic2.setId("2");
        topic2.setDescription("Other discussion");

        when(topicRepository.findAll()).thenReturn(Arrays.asList(topic1, topic2));

        List<TopicResponseDTO> results = topicService.findAll().stream()
                .map(topic -> {
                    TopicResponseDTO dto = new TopicResponseDTO();
                    dto.setId(topic.getId());
                    dto.setDescription(topic.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("1", results.get(0).getId());
        assertEquals("Discussion about architectural patterns", results.get(0).getDescription());
        assertEquals("2", results.get(1).getId());
        assertEquals("Other discussion", results.get(1).getDescription());
    }

    @Test
    public void testCreateSession() {
        CreateSessionRequestDTO request = new CreateSessionRequestDTO();
        request.setTopicId("1");
        request.setSessionDurationMinutes(1);

        Topic topic = new Topic();
        topic.setId("1");
        topic.setDescription("Discussion about architectural patterns");

        when(topicRepository.findById(request.getTopicId())).thenReturn(Optional.of(topic));
        when(topicRepository.save(any(Topic.class))).thenReturn(topic);

        TopicSessionResponseDTO result = topicService.createSession(request);
        assertNotNull(result.getSessionStartTime());
        assertNotNull(result.getSessionEndTime());
    }

    @Test
    public void testRegisterVoteSuccess() {
        Topic topic = new Topic();
        topic.setId("1");
        topic.setDescription("Discussion about architectural patterns");

        CreateSessionRequestDTO request = new CreateSessionRequestDTO();
        request.setTopicId("1");
        request.setSessionDurationMinutes(1);

        topic.setSessionStartTime(LocalDateTime.now());
        topic.setSessionEndTime(topic.getSessionStartTime().plusMinutes(request.getSessionDurationMinutes()));

        Associate associate = new Associate();
        associate.setId("1");
        associate.setName("Adalberto Junior");
        associate.setCpf("85905603090");

        VoteRequestDTO vote = new VoteRequestDTO();
        vote.setTopicId("1");
        vote.setAssociateId("1");
        vote.setVote(true);

        when(topicRepository.findById(any())).thenReturn(Optional.of(topic));
        when(associateService.findById(any())).thenReturn(associate);

        topicService.registerVote(vote);
    }

    @Test(expected = SessionNotAvailableException.class)
    public void testRegisterVoteSessionNotOpen() {
        Topic topic = new Topic();
        topic.setId("1");
        topic.setDescription("Discussion about architectural patterns");

        Associate associate = new Associate();
        associate.setId("1");
        associate.setName("Adalberto Junior");
        associate.setCpf("85905603090");

        VoteRequestDTO vote = new VoteRequestDTO();
        vote.setTopicId("1");
        vote.setAssociateId("1");
        vote.setVote(true);

        when(topicRepository.findById(any())).thenReturn(Optional.of(topic));

        topicService.registerVote(vote);
    }

    @Test(expected = SessionNotValidForVoteException.class)
    public void testRegisterVoteSessionExpired() {
        Topic topic = new Topic();
        topic.setId("1");
        topic.setDescription("Discussion about architectural patterns");

        CreateSessionRequestDTO request = new CreateSessionRequestDTO();
        request.setTopicId("1");
        request.setSessionDurationMinutes(1);


        topic.setSessionStartTime(LocalDateTime.of(2024, 4, 15, 9, 0));
        topic.setSessionEndTime(LocalDateTime.of(2024, 4, 15, 9, 1));

        Associate associate = new Associate();
        associate.setId("1");
        associate.setName("Adalberto Junior");
        associate.setCpf("85905603090");

        VoteRequestDTO vote = new VoteRequestDTO();
        vote.setTopicId("1");
        vote.setAssociateId("1");
        vote.setVote(true);

        when(topicRepository.findById(any())).thenReturn(Optional.of(topic));

        topicService.registerVote(vote);
    }

    @Test
    public void testGenerateResult() {
        Topic topic = new Topic();
        topic.setId("1");
        topic.setDescription("Discussion about architectural patterns");

        topic.setSessionStartTime(LocalDateTime.now());
        topic.setSessionEndTime(topic.getSessionStartTime().plusMinutes(1));

        Associate associate1 = new Associate();
        associate1.setId("1");
        associate1.setName("Adalberto Junior");
        associate1.setCpf("85905603090");

        Associate associate2 = new Associate();
        associate2.setId("2");
        associate2.setName("Adalberto Neto");
        associate2.setCpf("02272826040");

        List<Vote> votes = new ArrayList<>();
        votes.add(Vote.builder().id("1").associate(associate1).associateVote(true).build());
        votes.add(Vote.builder().id("2").associate(associate2).associateVote(false).build());
        topic.setVotes(votes);

        when(topicRepository.findById(any())).thenReturn(Optional.of(topic));

        topicService.generateResult("1");
    }
}