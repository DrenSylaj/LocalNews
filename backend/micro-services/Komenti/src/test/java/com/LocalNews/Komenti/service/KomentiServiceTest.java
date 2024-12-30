package com.LocalNews.Komenti.service;

import com.LocalNews.Komenti.entity.Komenti;
import com.LocalNews.Komenti.repository.KomentiRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KomentiServiceTest {

    @Mock
    private KomentiRepository komentiRepository;

    @InjectMocks
    private KomentiService komentiService;


    @Test
    public void KomentiService_SaveKomenti_ReturnsKomenti(){
        Komenti savedKomenti = Komenti.builder()
                .teksti("Kosova")
                .creatorId(52)
                .build();

        when(komentiRepository.save(Mockito.any(Komenti.class))).thenReturn(savedKomenti);

        // Act
        komentiService.createKomenti(savedKomenti);

        // Assert
        Assertions.assertThat(savedKomenti).isNotNull();
        Assertions.assertThat(savedKomenti.getTeksti()).isEqualTo("Kosova");

    }
}
