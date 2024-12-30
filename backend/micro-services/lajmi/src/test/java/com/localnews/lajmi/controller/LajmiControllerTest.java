package com.localnews.lajmi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.service.LajmiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = LajmiController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class LajmiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LajmiService lajmiService;

    @Autowired
    private ObjectMapper objectMapper;

    private Lajmi lajmi;
    private Kategoria kategoria;

    @BeforeEach
    public void init(){
        kategoria = Kategoria.builder().name("Sport").build();
        lajmi = Lajmi.builder().teksti("Kosova").kategoria(kategoria).build();
    }

    @Test
    public void LajmiController_GetAllLajmet_ReturnOk() throws Exception {

        List<Lajmi> lajmetList = Arrays.asList(new Lajmi(), new Lajmi());
        given(lajmiService.findAllLajmet()).willReturn(lajmetList);


        ResultActions response = mockMvc.perform(get("/api/v1/lajmet")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(lajmetList.size()));
    }


}
