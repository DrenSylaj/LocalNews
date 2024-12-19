package com.localnews.lajmi.service;

import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.entity.Lajmi;
import com.localnews.lajmi.repository.KategoriaRepository;
import com.localnews.lajmi.repository.LajmiRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LajmiServiceTest {

    @Mock
    private LajmiRepository lajmiRepository;

    @Mock
    private KategoriaRepository kategoriaRepository;

    @InjectMocks
    private LajmiService lajmiService;

    @Test
    public void LajmiService_CreateLajmi_ReturnsLajmi(){
        Kategoria kategoria = Kategoria.builder()
                .name("Sport")
                .build();


        Lajmi lajmi = Lajmi.builder()
                .teksti("Kosova")
                .kategoria(kategoria)
                .build();

        Kategoria savedKategoria = kategoriaRepository.save(kategoria);
        lajmi.setKategoria(savedKategoria);


        when(lajmiRepository.save(Mockito.any(Lajmi.class))).thenReturn(lajmi);

        lajmiService.saveLajmi(lajmi);

        Assertions.assertThat(lajmi).isNotNull();


    }

    @Test
    public void LajmiService_GetAllLajmet_ReturnsLajmiList() {
        // Arrange
        Kategoria kategoria = Kategoria.builder()
                .name("Sport")
                .build();

        Lajmi lajmi = Lajmi.builder()
                .teksti("Kosova")
                .kategoria(kategoria)
                .build();

        Lajmi lajmi2 = Lajmi.builder()
                .teksti("Kosova")
                .kategoria(kategoria)
                .build();


        List<Lajmi> lajmet = List.of(lajmi, lajmi2);
        when(lajmiRepository.findAll()).thenReturn(lajmet);

        // Act
        List<Lajmi> savedLajmet = lajmiService.findAllLajmet();

        // Assert
        Assertions.assertThat(savedLajmet).isNotNull();
        Assertions.assertThat(savedLajmet).hasSize(2);
        Assertions.assertThat(savedLajmet.get(0).getTeksti()).isEqualTo("Kosova");
        Assertions.assertThat(savedLajmet.get(0).getKategoria().getName()).isEqualTo("Sport");
    }

    @Test
    public void LajmiService_GetLajmiById_ReturnLajmi(){
        Kategoria kategoria = Kategoria.builder()
                .name("Sport")
                .build();

        Lajmi lajmi = Lajmi.builder()
                .id(1)
                .teksti("Kosova")
                .kategoria(kategoria)
                .build();

        when(lajmiRepository.findById(lajmi.getId())).thenReturn(Optional.of(lajmi));

        Lajmi savedLajmi = lajmiService.findById(lajmi.getId());

        Assertions.assertThat(savedLajmi).isNotNull();
        Assertions.assertThat(savedLajmi.getTeksti()).isEqualTo("Kosova");

    }

}
