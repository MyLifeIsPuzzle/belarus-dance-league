package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findAllByActiveTrue();

    List<Role> findAllByActiveFalse();
}
