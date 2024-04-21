package com.sicredi.vote.domain.service;

import com.sicredi.vote.adapter.dto.request.CreateSessionRequestDTO;
import com.sicredi.vote.adapter.dto.request.TopicRequestDTO;
import com.sicredi.vote.adapter.dto.request.VoteRequestDTO;
import com.sicredi.vote.adapter.dto.response.TopicResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicResultResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicSessionResponseDTO;
import com.sicredi.vote.adapter.mapper.TopicMapper;
import com.sicredi.vote.domain.enumerator.ResultType;
import com.sicredi.vote.domain.model.Associate;
import com.sicredi.vote.domain.model.Topic;
import com.sicredi.vote.domain.model.Vote;
import com.sicredi.vote.domain.repository.TopicRepository;
import com.sicredi.vote.infrastructure.exceptions.SessionNotAvailableException;
import com.sicredi.vote.infrastructure.exceptions.SessionNotValidForVoteException;
import com.sicredi.vote.infrastructure.exceptions.TopicNotFoundException;
import com.sicredi.vote.infrastructure.exceptions.UserAlreadyVotedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sicredi.vote.adapter.mapper.TopicMapper.topicEntityToResponse;
import static com.sicredi.vote.adapter.mapper.TopicMapper.topicSessionEntityToResponse;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topic;
    private final AssociateService associateService;

    private final VoteValidatorService voteValidatorService;

    public TopicResponseDTO save(TopicRequestDTO topicRequestDTO) {
        Topic topic = this.topic.save(Topic.builder()
                .description(topicRequestDTO.getDescription())
                .build());

        return topicEntityToResponse(topic);
    }

    public Topic findById(String id) {
        return this.topic.findById(id)
                .orElseThrow(TopicNotFoundException::new);
    }

    public List<TopicResponseDTO> findAll() {
        return this.topic.findAll().stream()
                .map(TopicMapper::topicEntityToResponse
                ).collect(Collectors.toList());
    }

    public TopicSessionResponseDTO createSession(CreateSessionRequestDTO createSessionRequestDTO) {
        Topic topic = findById(createSessionRequestDTO.getTopicId());
        LocalDateTime now = LocalDateTime.now();

        topic.setSessionStartTime(now);
        topic.setSessionEndTime(now.plusMinutes(createSessionRequestDTO.getSessionDurationMinutes()));

        Topic topicSessionEntity = this.topic.save(topic);

        return topicSessionEntityToResponse(topicSessionEntity);
    }

    public void registerVote(VoteRequestDTO voteRequestDTO) {
        Topic topic = findById(voteRequestDTO.getTopicId());
        validateSessionTiming(topic);

        Associate associate = associateService.findById(voteRequestDTO.getAssociateId());
        voteValidatorService.validateVote(associate.getCpf());

        Vote vote = Vote.builder()
                .associate(associate)
                .associateVote(voteRequestDTO.isVote())
                .build();

        if (topic.getVotes() == null) {
            topic.setVotes(new ArrayList<>());
        }

        checkIfAssociateAlreadyVoted(topic, associate);

        topic.getVotes().add(vote);

        this.topic.save(topic);
    }

    public TopicResultResponseDTO generateResult(String id) {
        Topic topic = findById(id);

        if (topic.getVotes() != null) {
            int total = topic.getVotes().size();
            int yes = (int) topic.getVotes().stream().filter(Vote::isAssociateVote).count();
            int no = total - yes;

            return TopicResultResponseDTO.builder()
                    .totalVotes(total)
                    .yesVotes(yes)
                    .noVotes(no)
                    .mostVoted(yes > no ? ResultType.YES : yes < no ? ResultType.NO : ResultType.DRAW)
                .build();
        }

        return TopicResultResponseDTO.builder().build();
    }

    private void validateSessionTiming(Topic topic) {
        LocalDateTime now = LocalDateTime.now();

        if (topic.getSessionStartTime() == null || topic.getSessionEndTime() == null) {
            throw new SessionNotAvailableException();
        }

        if (now.isBefore(topic.getSessionStartTime()) || now.isAfter(topic.getSessionEndTime())) {
            throw new SessionNotValidForVoteException();
        }
    }

    private void checkIfAssociateAlreadyVoted(Topic topic, Associate associate) {
        boolean hasVote = topic.getVotes().stream()
                .anyMatch(vote -> vote.getAssociate().getId().equals(associate.getId()));

        if (hasVote) {
            throw new UserAlreadyVotedException();
        }
    }
}
