package com.artsiomtretinnikov.repository;

import java.util.List;

public interface ActiveRepository<T> {

    List<T> findAllByActive(Class<T> type, boolean active);
}
