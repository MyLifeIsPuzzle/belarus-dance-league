package com.artsiomtretinnikov.converter;

import com.artsiomtretinnikov.entity.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter {

    public UserDetails convert(Account account) {
        return User
                .builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .authorities(new SimpleGrantedAuthority(account.getRole().getName()))
                .build();
    }
}
