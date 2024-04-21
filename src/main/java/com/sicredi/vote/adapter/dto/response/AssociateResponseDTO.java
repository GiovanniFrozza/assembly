package com.sicredi.vote.adapter.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociateResponseDTO {
    private String id;
    private String name;
    private String cpf;

}
