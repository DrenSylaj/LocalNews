package com.localnews.user.services;

import com.localnews.user.DTO.AutoriDTO;
import com.localnews.user.config.JwtService;
import com.localnews.user.entities.Autori;
import com.localnews.user.entities.User;
import com.localnews.user.repositories.AutoriRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutoriService {

    private final AutoriRepository autoriRepository;
    private final JwtService jwtService;
    public Autori findByUserId(Integer userId){
        return autoriRepository.findByUserId(userId);
    }

    public AutoriDTO findByJwt(String jwt){
        User user = jwtService.findUserByJwt(jwt).orElseThrow();
        Autori autori = findByUserId(user.getId());

        return AutoriDTO.builder()
                .id(autori.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .bibliografia(autori.getBibliografia())
                .dataLindjes(user.getDataLindjes())
                .vendbanimi(user.getVendbanimi())
                .gjinia(user.getGjinia())
                .build();
    }
}
