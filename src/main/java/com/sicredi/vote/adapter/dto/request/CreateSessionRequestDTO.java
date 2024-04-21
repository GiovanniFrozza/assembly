package com.sicredi.vote.adapter.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSessionRequestDTO {
    @Schema(description = "Id of the topic", example = "6623fa79c243656c3afb2f0c")
    @NotBlank(message = "The field [topicId] is required and cannot be empty.")
    private String topicId;
    @Schema(description = "session duration", example = "1")
    @Builder.Default
    private Integer sessionDurationMinutes = 1;
}
