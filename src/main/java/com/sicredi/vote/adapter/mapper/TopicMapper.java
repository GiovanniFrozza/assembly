package com.sicredi.vote.adapter.mapper;

import com.sicredi.vote.adapter.dto.response.TopicResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicSessionResponseDTO;
import com.sicredi.vote.domain.model.Topic;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class TopicMapper {

    public static TopicResponseDTO topicEntityToResponse(Topic topic) {
        return TopicResponseDTO.builder()
                .id(topic.getId())
                .description(topic.getDescription())
                .votes(
                        Optional.ofNullable(topic.getVotes())
                                .orElse(new ArrayList<>()).stream().map(VoteMapper::voteEntityToResponse).collect(Collectors.toList()))
                .build();
    }


    public static TopicSessionResponseDTO topicSessionEntityToResponse(Topic topic) {
        return TopicSessionResponseDTO.builder()
                .description(topic.getDescription())
                .sessionStartTime(topic.getSessionStartTime())
                .sessionEndTime(topic.getSessionEndTime())
                .build();
    }

}
