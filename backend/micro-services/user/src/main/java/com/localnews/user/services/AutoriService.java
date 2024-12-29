package com.localnews.user.services;

import com.localnews.user.DTO.AutoriDTO;
import com.localnews.user.config.JwtService;
import com.localnews.user.entities.Autori;
import com.localnews.user.entities.Role;
import com.localnews.user.entities.User;
import com.localnews.user.repositories.AutoriRepository;
import com.localnews.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutoriService {

    private final AutoriRepository autoriRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;
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

    public ResponseEntity<String> updateRole(@PathVariable("userId") Integer userId, @RequestBody Autori autori){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("A User with id: "+userId+" doesn't exist in our database"));

        user.setRole(Role.valueOf("ROLE_AUTHOR"));

        autori.setUserId(user.getId());

        if(findByUserId(userId) == null){
            autoriRepository.save(autori);
        }
        return ResponseEntity.ok("Role updated and Autori saved (if needed)");
    }
}
