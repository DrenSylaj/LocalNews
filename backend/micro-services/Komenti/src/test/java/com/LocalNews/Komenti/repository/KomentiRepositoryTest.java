package com.LocalNews.Komenti.repository;

import com.LocalNews.Komenti.entity.Komenti;
import jakarta.ws.rs.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class KomentiRepositoryTest {

    @Autowired
    private KomentiRepository komentiRepository;

    @Test
    public void KomentiReposityTest_getKomentiById_Komenti(){
        Komenti savedKomenti = Komenti.builder()
                .teksti("Kosova123")
                .creatorId(52)
                .build();
        komentiRepository.save(savedKomenti);

        Komenti komenti = komentiRepository.findById(savedKomenti.getId()).orElseThrow(()-> new NotFoundException("Komenti me id: "+savedKomenti.getId()+" nuk u gjet!"));

        Assertions.assertThat(komenti).isNotNull();
        Assertions.assertThat(komenti.getId()).isEqualTo(savedKomenti.getId());
    }

    @Test
    public void KomentiRepositroy_saveLajmi_ReturnsKomenti(){
        Komenti savedKomenti = Komenti.builder()
                .teksti("Kosova123")
                .creatorId(52)
                .build();

        komentiRepository.save(savedKomenti);

        Assertions.assertThat(savedKomenti).isNotNull();
        Assertions.assertThat(savedKomenti.getId()).isGreaterThan(0);
    }


}
