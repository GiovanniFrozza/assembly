package com.sicredi.vote.domain.service;

import com.sicredi.vote.adapter.dto.request.AssociateRequestDTO;
import com.sicredi.vote.adapter.dto.response.AssociateResponseDTO;
import com.sicredi.vote.adapter.mapper.AssociateMapper;
import com.sicredi.vote.domain.model.Associate;
import com.sicredi.vote.domain.repository.AssociateRepository;
import com.sicredi.vote.infrastructure.exceptions.AssociateNotFoundException;
import com.sicredi.vote.infrastructure.exceptions.CpfAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sicredi.vote.adapter.mapper.AssociateMapper.associateEntityToResponse;

@Service
@AllArgsConstructor
public class AssociateService {

    private final AssociateRepository associate;
    private final CpfValidatorService cpfValidatorService;

    public AssociateResponseDTO save(AssociateRequestDTO associateRequestDTO) {
        String cpf = resolveCpf(associateRequestDTO.getCpf());
        verifyDuplicatedCpf(cpf);
        cpfValidatorService.validateCpf(cpf);

        Associate associate = this.associate.save(Associate.builder()
                .name(associateRequestDTO.getName())
                .cpf(cpf)
                .build());

        return associateEntityToResponse(associate);
    }

    public Associate findById(String id) {
        return this.associate.findById(id)
                .orElseThrow(AssociateNotFoundException::new);
    }

    public List<AssociateResponseDTO> findAll() {
        return this.associate.findAll().stream()
                .map(AssociateMapper::associateEntityToResponse
                ).collect(Collectors.toList());
    }

    private void verifyDuplicatedCpf(String cpf) {
        boolean cpfExists = this.associate.findByCpf(cpf).isPresent();

        if (cpfExists) {
            throw new CpfAlreadyExistsException();
        }
    }

    private String resolveCpf(String cpf) {
        return cpf.replaceAll("\\W", "");
    }
}
