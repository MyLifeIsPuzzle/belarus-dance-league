package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.converter.UserDetailsConverter;
import com.artsiomtretinnikov.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final AccountRepository accountRepository;
    private final UserDetailsConverter detailsConverter;


    @Autowired
    public UserServiceImpl(AccountRepository accountRepository, UserDetailsConverter detailsConverter) {
        this.accountRepository = accountRepository;
        this.detailsConverter = detailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.of(email)
                .map(it -> accountRepository.findByEmail(email))
                .map(it -> detailsConverter.convert(it.get()))
                .orElseThrow(() -> new UsernameNotFoundException("Account does not exist!"));
    }
}
