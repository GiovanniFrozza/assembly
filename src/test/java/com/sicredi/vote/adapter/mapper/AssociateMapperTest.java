package com.sicredi.vote.adapter.mapper;

import com.sicredi.vote.adapter.dto.response.AssociateResponseDTO;
import com.sicredi.vote.domain.model.Associate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AssociateMapperTest {
    @Test
    public void testAssociateEntityToResponse() {
        Associate associate = new Associate();
        associate.setId("123");
        associate.setName("Adalberto Pereira Junior");
        associate.setCpf("123.456.789-01");

        AssociateResponseDTO dto = AssociateMapper.associateEntityToResponse(associate);

        assertEquals("123", dto.getId());
        assertEquals("Adalberto Pereira Junior", dto.getName());
        assertEquals("123.456.789-01", dto.getCpf());
    }
}