package com.localnews.user.services;

import com.localnews.user.entities.Role;
import com.localnews.user.entities.User;
import com.localnews.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CostumerUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(username).orElseThrow();
        if(user == null){
            throw new UsernameNotFoundException("User not found with email: "+username);
        }

        Role role = user.getRole();
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
