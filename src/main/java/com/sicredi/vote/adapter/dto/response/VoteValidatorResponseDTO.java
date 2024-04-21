package com.sicredi.vote.adapter.dto.response;

import com.sicredi.vote.domain.enumerator.StatusVote;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteValidatorResponseDTO {
    private StatusVote statusVote;
}
