package com.localnews.lajmi.repository;

import com.localnews.lajmi.entity.Kategoria;
import com.localnews.lajmi.entity.Lajmi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LajmiRepositoryTest {
    @Autowired
    private LajmiRepository lajmiRepository;

    @Autowired
    KategoriaRepository kategoriaRepository;

    @Test
    public void LajmiRepository_SaveAll_ReturnSavedLajmi(){
        //Arrange
        Kategoria kategoria = Kategoria.builder()
                .name("Sport")
                .build();


        Lajmi lajmi = Lajmi.builder()
                .teksti("Kosova")
                .kategoria(kategoria)
                .build();

        Kategoria savedKategoria = kategoriaRepository.save(kategoria);
        lajmi.setKategoria(savedKategoria);

        //Act
        Lajmi savedLajmi = lajmiRepository.save(lajmi);

        //Assert
        Assertions.assertThat(savedLajmi).isNotNull();
        Assertions.assertThat(savedLajmi.getId()).isGreaterThan(0);
    }

    @Test
    public void LajmiRepository_GetAll_ReturnMoreThanOneLajmi() {
        //Arrange
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

        Kategoria savedKategoria = kategoriaRepository.save(kategoria);
        lajmi.setKategoria(savedKategoria);
        lajmi2.setKategoria(savedKategoria);

        //Act
        lajmiRepository.save(lajmi);
        lajmiRepository.save(lajmi2);
        List<Lajmi> lajmiList = lajmiRepository.findAll();

        //Assert
        Assertions.assertThat(lajmiList).isNotNull();
        Assertions.assertThat(lajmiList.size()).isEqualTo(2);
    }

    @Test
    public void LajmiRepository_FindById_ReturnLajmi(){
        Kategoria kategoria = Kategoria.builder()
                .name("Sport")
                .build();


        Lajmi lajmi = Lajmi.builder()
                .teksti("Kosova")
                .kategoria(kategoria)
                .build();

        Kategoria savedKategoria = kategoriaRepository.save(kategoria);
        lajmi.setKategoria(savedKategoria);
        Lajmi savedLajmi = lajmiRepository.save(lajmi);

        Lajmi foundLajmi = lajmiRepository.findById(savedLajmi.getId()).get();

        Assertions.assertThat(foundLajmi).isNotNull();
    }

    @Test
    public void LajmiRepository_FindByKategoria_ReturnLajmiList(){
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

        Kategoria savedKategoria = kategoriaRepository.save(kategoria);
        lajmi.setKategoria(savedKategoria);
        lajmi2.setKategoria(savedKategoria);
        lajmiRepository.save(lajmi);
        lajmiRepository.save(lajmi2);

        List<Lajmi> lajmiList = lajmiRepository.findByKategoria(savedKategoria);

        Assertions.assertThat(lajmiList).isNotNull();
        Assertions.assertThat(lajmiList).size().isEqualTo(2);
    }

    @Test
    public void LajmiRepository_DeleteById_ReturnLajmiIsEmpty(){
        Kategoria kategoria = Kategoria.builder()
                .name("Sport")
                .build();

        Lajmi lajmi = Lajmi.builder()
                .teksti("Kosova")
                .kategoria(kategoria)
                .build();

        Kategoria savedKategoria = kategoriaRepository.save(kategoria);
        lajmi.setKategoria(savedKategoria);
        lajmiRepository.save(lajmi);

        lajmiRepository.deleteById(lajmi.getId());
        Optional<Lajmi> lajmiReturn = lajmiRepository.findById(lajmi.getId());

        Assertions.assertThat(lajmiReturn).isEmpty();
    }
}
