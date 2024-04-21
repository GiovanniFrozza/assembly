package com.sicredi.vote.adapter.dto.request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AssociateRequestDTOTest {

    @Test
    public void testNoArgsConstructor() {
        AssociateRequestDTO dto = new AssociateRequestDTO();
        assertNull(dto.getName());
        assertNull(dto.getCpf());
    }

    @Test
    public void testAllArgsConstructor() {
        AssociateRequestDTO dto = new AssociateRequestDTO("Adalberto Pereira Junior", "51434347001");
        assertEquals("Adalberto Pereira Junior", dto.getName());
        assertEquals("51434347001", dto.getCpf());
    }

    @Test
    public void testBuilder() {
        AssociateRequestDTO dto = AssociateRequestDTO.builder()
                .name("Adalberto Pereira Junior")
                .cpf("51434347001")
                .build();
        assertEquals("Adalberto Pereira Junior", dto.getName());
        assertEquals("51434347001", dto.getCpf());
    }

    @Test
    public void testSettersAndGetters() {
        AssociateRequestDTO dto = new AssociateRequestDTO();
        dto.setName("Adalberto Pereira Junior");
        dto.setCpf("51434347001");
        assertEquals("Adalberto Pereira Junior", dto.getName());
        assertEquals("51434347001", dto.getCpf());
    }

}