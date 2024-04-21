package com.sicredi.vote.adapter.resource;

import com.sicredi.vote.adapter.dto.request.AssociateRequestDTO;
import com.sicredi.vote.adapter.dto.response.AssociateResponseDTO;
import com.sicredi.vote.domain.service.AssociateService;
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
@RequiredArgsConstructor
@RestController
@Tag(name = "associate-controller", description = "Operations related to Associates in the system")
@RequestMapping("/associate")
public class AssociateController {

    private final AssociateService associateService;

    @Operation(summary = "Create a new associate", description = "Registers a new associate in the system.")
    @ApiResponse(responseCode = "201", description = "Associate created successfully", content = @Content(schema = @Schema(implementation = AssociateResponseDTO.class)))
    @ApiResponse(responseCode = "409", description = "Cpf Already Exists", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody AssociateRequestDTO associateRequestDTO) {
        log.info("Creating new associate");

        var result = this.associateService.save(associateRequestDTO);
        log.info("Associate created successfully with ID: {}", result.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Find all associates", description = "Find all associates.")
    @ApiResponse(responseCode = "200", description = "Associates found successfully", content = @Content(schema = @Schema(implementation = AssociateResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    @GetMapping
    public ResponseEntity<Object> findAll() {
        log.info("Attempting to find all associates");

        var result = this.associateService.findAll();
        log.info("Associates found successfully");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
