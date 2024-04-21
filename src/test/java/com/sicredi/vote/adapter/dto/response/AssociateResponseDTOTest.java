package com.sicredi.vote.adapter.dto.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)

public class AssociateResponseDTOTest {
    @Test
    public void testNoArgsConstructor() {
        AssociateResponseDTO dto = new AssociateResponseDTO();
        assertNull(dto.getId());
        assertNull(dto.getName());
        assertNull(dto.getCpf());
    }

    @Test
    public void testAllArgsConstructor() {
        AssociateResponseDTO dto = new AssociateResponseDTO("1", "Adalberto Pereira Junior", "12345678901");
        assertEquals("1", dto.getId());
        assertEquals("Adalberto Pereira Junior", dto.getName());
        assertEquals("12345678901", dto.getCpf());
    }

    @Test
    public void testBuilder() {
        AssociateResponseDTO dto = AssociateResponseDTO.builder()
                .id("1")
                .name("Adalberto Pereira Junior")
                .cpf("12345678901")
                .build();
        assertEquals("1", dto.getId());
        assertEquals("Adalberto Pereira Junior", dto.getName());
        assertEquals("12345678901", dto.getCpf());
    }

    @Test
    public void testSettersAndGetters() {
        AssociateResponseDTO dto = new AssociateResponseDTO();
        dto.setId("1");
        dto.setName("Adalberto Pereira Junior");
        dto.setCpf("12345678901");

        assertEquals("1", dto.getId());
        assertEquals("Adalberto Pereira Junior", dto.getName());
        assertEquals("12345678901", dto.getCpf());
    }
}