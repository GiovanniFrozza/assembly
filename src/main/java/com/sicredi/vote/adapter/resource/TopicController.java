package com.sicredi.vote.adapter.resource;

import com.sicredi.vote.adapter.dto.request.CreateSessionRequestDTO;
import com.sicredi.vote.adapter.dto.request.TopicRequestDTO;
import com.sicredi.vote.adapter.dto.request.TopicResultRequestDTO;
import com.sicredi.vote.adapter.dto.request.VoteRequestDTO;
import com.sicredi.vote.adapter.dto.response.AssociateResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicResponseDTO;
import com.sicredi.vote.adapter.dto.response.TopicResultResponseDTO;
import com.sicredi.vote.domain.service.TopicService;
import com.sicredi.vote.infrastructure.exceptions.ErrorMessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "topic-controller", description = "Operations related to Topics in the system")
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    @Operation(summary = "Create a new topic", description = "This operation creates a new topic with a description.")
    @ApiResponse(responseCode = "201", description = "Topic created successfully", content = @Content(schema = @Schema(implementation = TopicResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody TopicRequestDTO topicRequestDTO) {
        log.info("Attempting to create a new topic");

        var result = this.topicService.save(topicRequestDTO);
        log.info("Topic created successfully with ID: {}", result.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Create a session", description = "Creates a session for a specified topic with a duration.")
    @ApiResponse(responseCode = "201", description = "Session created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @PutMapping("/sessions/{id}")
    public ResponseEntity<Object> createSession(@Valid @RequestBody CreateSessionRequestDTO createSessionRequestDTO) {
        log.info("Attempting to create a session for topic ID: {} with duration: {} minutes", createSessionRequestDTO.getTopicId(), createSessionRequestDTO.getSessionDurationMinutes());

        var result = this.topicService.createSession(createSessionRequestDTO);
        log.info("Session created successfully for topic ID: {}", createSessionRequestDTO.getTopicId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Find all topics", description = "Search all topics")
    @ApiResponse(responseCode = "200", description = "Topic found successfully", content = @Content(schema = @Schema(implementation = TopicResponseDTO.class)))
    @GetMapping
    public ResponseEntity<Object> findAll() {
        log.info("Attempting to find all topics");

        var result = this.topicService.findAll();
        log.info("Topics found successfully");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Register a vote", description = "Register a vote by an associate on a specific topic.")
    @ApiResponse(responseCode = "200", description = "Vote registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @ApiResponse(responseCode = "404", description = "Topic or Associate not found", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @ApiResponse(responseCode = "500", description = "Error during voting process", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @PutMapping("/vote")
    public ResponseEntity<Object> registerVote(@Valid @RequestBody VoteRequestDTO voteRequestDTO) {
        log.info("Attempting to register vote for topic ID: {}", voteRequestDTO.getTopicId());

        this.topicService.registerVote(voteRequestDTO);
        log.info("Vote registered successfully for topic ID: {}", voteRequestDTO.getTopicId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Result votes", description = "Return results of votes by topic")
    @ApiResponse(responseCode = "200", description = "Result votes generated successfully", content = @Content(schema = @Schema(implementation = TopicResultResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @ApiResponse(responseCode = "404", description = "Topic not found", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @PostMapping("/resultVote")
    public ResponseEntity<Object> resultVote(@Valid @RequestBody TopicResultRequestDTO topicResultRequestDTO) {
        log.info("Attempting result votes for topic ID: {}", topicResultRequestDTO.getTopicId());

        var result = this.topicService.generateResult(topicResultRequestDTO.getTopicId());
        log.info("Result votes successfully");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
