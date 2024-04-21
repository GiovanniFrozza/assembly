package com.sicredi.vote.adapter.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponseDTO {
    private String id;
    private String description;
    private List<VoteResponseDTO> votes;
}
