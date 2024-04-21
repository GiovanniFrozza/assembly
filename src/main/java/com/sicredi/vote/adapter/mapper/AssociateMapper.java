package com.sicredi.vote.adapter.mapper;

import com.sicredi.vote.adapter.dto.response.AssociateResponseDTO;
import com.sicredi.vote.domain.model.Associate;

public class AssociateMapper {

    public static AssociateResponseDTO associateEntityToResponse(Associate associate) {
        return AssociateResponseDTO.builder()
                .id(associate.getId())
                .name(associate.getName())
                .cpf(associate.getCpf())
                .build();
    }

}
