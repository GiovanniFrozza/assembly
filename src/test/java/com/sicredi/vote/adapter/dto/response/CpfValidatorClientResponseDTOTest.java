package com.sicredi.vote.adapter.dto.response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class CpfValidatorClientResponseDTOTest {

    @Test
    public void testNoArgsConstructor() {
        CpfValidatorResponseDTO dto = new CpfValidatorResponseDTO();
        assertFalse(dto.isValid());
        assertNull(dto.getFormatted());
    }

    @Test
    public void testAllArgsConstructor() {
        CpfValidatorResponseDTO dto = new CpfValidatorResponseDTO(true, "123.456.789-09");
        assertTrue(dto.isValid());
        assertEquals("123.456.789-09", dto.getFormatted());
    }

    @Test
    public void testBuilder() {
        CpfValidatorResponseDTO dto = CpfValidatorResponseDTO.builder()
                .valid(true)
                .formatted("123.456.789-09")
                .build();
        assertTrue(dto.isValid());
        assertEquals("123.456.789-09", dto.getFormatted());
    }

    @Test
    public void testSettersAndGetters() {
        CpfValidatorResponseDTO dto = new CpfValidatorResponseDTO();
        dto.setValid(true);
        dto.setFormatted("123.456.789-09");

        assertTrue(dto.isValid());
        assertEquals("123.456.789-09", dto.getFormatted());
    }
}