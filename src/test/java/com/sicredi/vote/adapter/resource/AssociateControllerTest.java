package com.sicredi.vote.adapter.resource;

import com.sicredi.vote.adapter.dto.request.AssociateRequestDTO;
import com.sicredi.vote.adapter.dto.response.AssociateResponseDTO;
import com.sicredi.vote.domain.service.AssociateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssociateControllerTest {
    @InjectMocks
    private AssociateController associateController;
    @Mock
    private AssociateService associateService;

    @Test
    public void testCreateAssociate() {
        AssociateResponseDTO associateResponseDTO = new AssociateResponseDTO();
        associateResponseDTO.setId("1");
        associateResponseDTO.setName("Giovanni");
        associateResponseDTO.setCpf("23314888007");

        when(associateService.save(any())).thenReturn(associateResponseDTO);

        ResponseEntity<Object> result = associateController.create(new AssociateRequestDTO());
        assertNotNull(result.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    public void testFindAllAssociates() {
        AssociateResponseDTO associateResponseDTO1 = new AssociateResponseDTO();
        associateResponseDTO1.setId("1");
        associateResponseDTO1.setName("Giovanni");
        associateResponseDTO1.setCpf("23314888007");

        AssociateResponseDTO associateResponseDTO2 = new AssociateResponseDTO();
        associateResponseDTO2.setId("2");
        associateResponseDTO2.setName("Adalberto");
        associateResponseDTO2.setCpf("12345678901");

        List<AssociateResponseDTO> mockResponses = Arrays.asList(associateResponseDTO1, associateResponseDTO2);
        when(associateService.findAll()).thenReturn(mockResponses);

        ResponseEntity<Object> result = associateController.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}