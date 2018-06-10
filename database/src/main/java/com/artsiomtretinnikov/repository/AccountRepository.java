package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAllByActiveTrue();

    List<Account> findAllByActiveFalse();

    Optional<Account> findByEmail(String email);
}
