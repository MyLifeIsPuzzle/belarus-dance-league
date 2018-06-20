package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, ActiveRepository<Role>{
}
