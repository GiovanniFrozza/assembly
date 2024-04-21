package com.sicredi.vote.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "associates")
public class Associate {
    @Id
    private String id;
    private String name;
    private String cpf;
}
