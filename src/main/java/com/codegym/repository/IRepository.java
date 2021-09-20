package com.codegym.repository;

import java.util.List;

public interface IRepository<T> {
    T findById(Long id);
    List<T> findAll();
    void save(T t);
    void remote(Long id);
}
