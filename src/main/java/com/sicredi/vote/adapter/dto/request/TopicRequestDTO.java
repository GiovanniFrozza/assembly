package com.sicredi.vote.adapter.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequestDTO {
    @Schema(description = "Description of the topic", example = "Discussão sobre padrões de projeto")
    @NotBlank(message = "The field [description] is required and cannot be empty.")
    private String description;
}
