package com.sicredi.vote.adapter.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CpfValidatorResponseDTO {
    private boolean valid;
    private String formatted;
}
