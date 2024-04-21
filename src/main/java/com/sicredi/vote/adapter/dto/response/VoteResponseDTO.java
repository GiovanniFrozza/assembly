package com.sicredi.vote.adapter.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {
    private AssociateResponseDTO associate;
    private boolean associateVote;
}
