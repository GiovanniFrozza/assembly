package com.sicredi.vote.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "votes")
public class Vote {
    @Id
    private String id;
    private Associate associate;
    private boolean associateVote;
}
