package com.sicredi.vote.adapter.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicSessionResponseDTO {
    private String description;
    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;
}
