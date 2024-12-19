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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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
    public void LajmiController_CreateLajmi_ReturnCreated() throws Exception{
    //        given(lajmiService.saveLajmi(ArgumentMatchers.any())).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        doNothing().when(lajmiService).saveLajmi(ArgumentMatchers.any(Lajmi.class));

        ResultActions response = mockMvc.perform(post("/api/v1/lajmet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(lajmi)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
