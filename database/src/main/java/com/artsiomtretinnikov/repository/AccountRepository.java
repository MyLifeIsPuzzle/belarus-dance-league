package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAllByActiveTrue();

    List<Account> findAllByActiveFalse();
}
