package com.ltp.pys;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.pys.entity.personel;
import com.ltp.pys.service.personService;

import lombok.NonNull;

import org.junit.jupiter.api.Test;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PysApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private personService personService;

    @Test
    public void testGetAllPersonel() throws Exception {
        int size = personService.gettAllPersonel().size();
        personel testPersonel1 = new personel();
        testPersonel1.setAd("Ali");
        testPersonel1.setSoyad("Kuş");
        testPersonel1.setTel_no("05333333312");
        testPersonel1.setDogum_tarihi("01-01-1990");
        testPersonel1.setUnvan("Software Engineer");
        testPersonel1.setMaas("5000");
        personService.saveOnePersonel(testPersonel1);
    
        assertEquals(size+1, personService.gettAllPersonel().size());
        mockMvc.perform(get("/personeller")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(size+1)))
                .andExpect(jsonPath("$[" + size + "].ad", is("Ali")));

        mockMvc.perform(delete("/personeller/{personelId}", testPersonel1.getId()));
    }
    


    @Test
    public void testCreatePersonel() throws Exception {
        personel newPersonel = new personel();
        newPersonel.setAd("Furkan");
        newPersonel.setSoyad("Gökoluk");
        newPersonel.setTel_no("10511111111");
        newPersonel.setDogum_tarihi("01-01-1995");
        newPersonel.setUnvan("Software Engineer");
        newPersonel.setMaas("4500");

        MvcResult result = mockMvc.perform(post("/personeller")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPersonel)))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        personel createdPersonel = objectMapper.readValue(responseBody, personel.class);

        mockMvc.perform(get("/personeller/{personelId}", createdPersonel.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ad", is("Furkan")));
        
        mockMvc.perform(delete("/personeller/{personelId}", createdPersonel.getId()));
    }

    @Test
    public void testUpdatePersonel() throws Exception {
        personel newPersonel = new personel();
        newPersonel.setAd("Enes");
        newPersonel.setSoyad("Gadiş");
        newPersonel.setTel_no("05345678901");
        newPersonel.setDogum_tarihi("01-01-1990");
        newPersonel.setUnvan("Software Engineer");
        newPersonel.setMaas("5000");

        personel savedPersonel = personService.saveOnePersonel(newPersonel);

        savedPersonel.setAd("Ahmet Enes");
        savedPersonel.setMaas("6000");

        mockMvc.perform(put("/personeller/{personelId}", savedPersonel.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedPersonel)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/personeller/{personelId}", savedPersonel.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ad", is("Ahmet Enes")))
                .andExpect(jsonPath("$.maas", is("6000")));

        mockMvc.perform(delete("/personeller/{personelId}", savedPersonel.getId()));
    }

        @Test
        public void testDeletePersonel() throws Exception {

        personel newPersonel = new personel();
        newPersonel.setAd("Selman");
        newPersonel.setSoyad("Aktaşçı");
        newPersonel.setTel_no("05545678901");
        newPersonel.setDogum_tarihi("01-01-1990");
        newPersonel.setUnvan("Software Engineer");
        newPersonel.setMaas("5000");

        personel savedPersonel = personService.saveOnePersonel(newPersonel);

        mockMvc.perform(delete("/personeller/{personelId}", savedPersonel.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        personel deletedPersonel = personService.getOnePersonel(savedPersonel.getId());
        assertNull(deletedPersonel);
	}
}
