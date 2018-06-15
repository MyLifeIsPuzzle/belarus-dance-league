package com.artsiomtretinnikov.repository;

import java.util.List;

public interface ActiveRepository<T> {

    List<T> findAllByActive(Class<?> type, boolean active);
}
