package com.sicredi.vote.adapter.dto.response;

import com.sicredi.vote.domain.enumerator.ResultType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicResultResponseDTO {
    private int totalVotes;
    private int yesVotes;
    private int noVotes;
    private ResultType mostVoted;
}
