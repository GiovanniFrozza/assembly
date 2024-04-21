package com.sicredi.vote.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "topics")
public class Topic {
    @Id
    private String id;
    private String description;
    private LocalDateTime sessionStartTime;
    private LocalDateTime sessionEndTime;
    private List<Vote> votes;
}
