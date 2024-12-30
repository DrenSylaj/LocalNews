package com.LocalNews.Komenti.controller;

import com.LocalNews.Komenti.client.UserClient;
import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.service.KomentiService;
import com.LocalNews.Komenti.service.LikeService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = KomentiController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class KomentiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KomentiService komentiService;

    @MockBean
    private LikeService likeService;

    @MockBean
    private UserClient userClient;

    @Autowired
    private ObjectMapper objectMapper;

    private Komenti komenti;

    @BeforeEach
    public void init(){
        komenti = Komenti.builder()
                .teksti("Kosova")
                .creatorId(1)
                .lajmiId(1)
                .build();
    }

    @Test
    public void KomentiController_getAllKomentet_returnsSavedKomentet() throws Exception{
        List<Komenti> komentet = List.of(komenti);
        when(komentiService.findAllKomentet()).thenReturn(komentet);

        ResultActions response = mockMvc.perform(get("/api/v1/komentet")
                .contentType(MediaType.APPLICATION_JSON));


        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
