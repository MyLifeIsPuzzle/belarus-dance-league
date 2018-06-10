package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

    List<Request> findAllByActiveTrue();

    List<Request> findAllByActiveFalse();
}
