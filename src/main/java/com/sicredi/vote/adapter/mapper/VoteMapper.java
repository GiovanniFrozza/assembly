package com.sicredi.vote.adapter.mapper;

import com.sicredi.vote.adapter.dto.response.VoteResponseDTO;
import com.sicredi.vote.domain.model.Vote;

public class VoteMapper {
    public static VoteResponseDTO voteEntityToResponse(Vote vote){
        return VoteResponseDTO.builder()
                .associate(AssociateMapper.associateEntityToResponse(vote.getAssociate()))
                .associateVote(vote.isAssociateVote())
                .build();
    }

}
