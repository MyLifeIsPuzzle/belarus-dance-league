package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long>, ActiveRepository<Request> {
}
