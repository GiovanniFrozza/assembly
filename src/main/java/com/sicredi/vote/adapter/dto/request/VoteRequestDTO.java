package com.sicredi.vote.adapter.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDTO {
    @Schema(description = "Id of the topic", example = "6623fa79c243656c3afb2f0c")
    @NotBlank(message = "The field [topicId] is required and cannot be empty.")
    private String topicId;
    @Schema(description = "Id of the associate", example = "6623131093943364f448e32e")
    @NotBlank(message = "The field [associateId] is required and cannot be empty.")
    private String associateId;
    private boolean vote;
}
