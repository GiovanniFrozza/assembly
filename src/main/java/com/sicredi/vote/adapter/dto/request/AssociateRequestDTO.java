package com.sicredi.vote.adapter.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssociateRequestDTO {
    @Schema(description = "Name of the associate", example = "Adalberto Pereira Junior")
    @NotBlank(message = "The field [name] is required and cannot be empty.")
    private String name;

    @Schema(description = "Cpf of the associate", example = "54409078070")
    @NotBlank(message = "The field [cpf] is required and cannot be empty.")
    private String cpf;
}
