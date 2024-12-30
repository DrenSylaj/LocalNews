package com.localnews.ankesa.Services;

import com.localnews.ankesa.Client.UserClient;
import com.localnews.ankesa.DTO.AnkesaRequest;
import com.localnews.ankesa.DTO.UserDTO;
import com.localnews.ankesa.Entity.Ankesa;
import com.localnews.ankesa.Repositories.AnkesaRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.Optional;

@Service
public class AnkesaService {

    @Autowired
    private AnkesaRepository ankesaRepository;
    @Autowired
    private UserClient userClient;

    public Ankesa saveAnkesa(Ankesa ankesa, String token) {

        UserDTO user = userClient.getUserByJwt(token);

        ankesa.setUserId(user.getId());

        return ankesaRepository.save(ankesa);
    }

    @CacheEvict(value = "ankesa", key="#id")
    public Ankesa updateAnkesa(Integer id, AnkesaRequest request, String token) throws NoPermissionException {
        Ankesa updated = ankesaRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ankesa nuk u gjete!")
        );

        UserDTO user = userClient.getUserByJwt(token);

        if (updated.getUserId() != user.getId())
            throw new NoPermissionException("Nuk keni autorizim per te bere update ankesen!");

        updated.setTema(request.getTema());
        updated.setLloji(request.getLloji());
        updated.setTeksti(request.getTeksti());

        return ankesaRepository.save(updated);
    }

    public List<Ankesa> getAllAnkesa() {
        return ankesaRepository.findAll();
    }

    @Cacheable(value = "ankesa", key="#id")
    public Ankesa getAnkesaById(Integer id) {
        System.out.println("Ankesa by Id called from DataBase(Ankesat)!");
        return ankesaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Ankesa me Id-ne e dhene nuk u gjete!")
        );
    }
    @CacheEvict(value = "ankesa", key="#id")
    public void deleteAnkesa(Integer id, String token) throws NoPermissionException {
        UserDTO user = userClient.getUserByJwt(token);

        Ankesa ankesa = ankesaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Ankesa nuk u gjete!")
        );

        System.out.println(user.getRole());
        System.out.println(user.getId() != ankesa.getUserId());

        if (user.getId() != ankesa.getUserId() && !user.getRole().toString().equals("ROLE_ADMIN"))
            throw new NoPermissionException("Nuk keni autorizim per te fshire kete koment!");

        ankesaRepository.deleteById(id);
    }


    public List<Ankesa> getAnkesatByUserId(Integer userId) {
        return ankesaRepository.findAllByUserId(userId);
    }
}
