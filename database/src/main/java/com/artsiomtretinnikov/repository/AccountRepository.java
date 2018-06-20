package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long>, ActiveRepository<Account> {

    Optional<Account> findByEmail(String email);
}
