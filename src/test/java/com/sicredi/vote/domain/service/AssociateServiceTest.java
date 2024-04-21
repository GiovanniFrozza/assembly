package com.sicredi.vote.domain.service;

import com.sicredi.vote.adapter.dto.request.AssociateRequestDTO;
import com.sicredi.vote.adapter.dto.response.AssociateResponseDTO;
import com.sicredi.vote.domain.model.Associate;
import com.sicredi.vote.domain.repository.AssociateRepository;
import com.sicredi.vote.infrastructure.exceptions.AssociateNotFoundException;
import com.sicredi.vote.infrastructure.exceptions.CpfAlreadyExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssociateServiceTest {

    @InjectMocks
    private AssociateService associateService;
    @Mock
    private AssociateRepository associateRepository;
    @Mock
    private CpfValidatorService cpfValidatorService;

    @Test
    public void testCreateAssociate() {
        AssociateRequestDTO associateRequestDTO = new AssociateRequestDTO();
        associateRequestDTO.setName("Giovanni");
        associateRequestDTO.setCpf("597.098.670-40");

        Associate mockAssociate = new Associate();
        mockAssociate.setName(associateRequestDTO.getName());
        mockAssociate.setCpf(associateRequestDTO.getCpf());

        when(associateRepository.save(Mockito.any(Associate.class))).thenReturn(mockAssociate);

        AssociateResponseDTO associateCreated = associateService.save(associateRequestDTO);

        assertEquals(associateRequestDTO.getName(), associateCreated.getName());
        assertEquals(associateRequestDTO.getCpf(), associateCreated.getCpf());
    }

    @Test(expected = CpfAlreadyExistsException.class)
    public void testCreateAssociateDuplicated() {
        AssociateRequestDTO associateRequestDTO = new AssociateRequestDTO();
        associateRequestDTO.setName("Giovanni");
        associateRequestDTO.setCpf("59709867044");

        when(associateRepository.findByCpf(associateRequestDTO.getCpf())).thenReturn(Optional.of(new Associate()));

        associateService.save(associateRequestDTO);

        verify(associateRepository, Mockito.never()).save(any(Associate.class));
    }

    @Test
    public void testFindByIdWhenFound() {
        String id = "1";
        Associate expectedAssociate = new Associate();
        expectedAssociate.setId(id);
        when(associateRepository.findById(id)).thenReturn(Optional.of(expectedAssociate));

        Associate result = associateService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test(expected = AssociateNotFoundException.class)
    public void testFindByIdWhenNotFound() {
        String id = "1";
        when(associateRepository.findById(id)).thenReturn(Optional.empty());

        associateService.findById(id);
    }

    @Test
    public void testFindAll() {
        Associate associate1 = new Associate();
        associate1.setId("1");
        associate1.setName("Giovanni");
        associate1.setCpf("12345678900");

        Associate associate2 = new Associate();
        associate2.setId("2");
        associate2.setName("Giovanni");
        associate2.setCpf("98765432100");

        when(associateRepository.findAll()).thenReturn(Arrays.asList(associate1, associate2));

        List<AssociateResponseDTO> results = associateService.findAll().stream()
                .map(associate -> {
                    AssociateResponseDTO dto = new AssociateResponseDTO();
                    dto.setId(associate.getId());
                    dto.setName(associate.getName());
                    dto.setCpf(associate.getCpf());
                    return dto;
                })
                .collect(Collectors.toList());

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("1", results.get(0).getId());
        assertEquals("Giovanni", results.get(0).getName());
        assertEquals("12345678900", results.get(0).getCpf());
        assertEquals("2", results.get(1).getId());
        assertEquals("Giovanni", results.get(1).getName());
        assertEquals("98765432100", results.get(1).getCpf());
    }
}
