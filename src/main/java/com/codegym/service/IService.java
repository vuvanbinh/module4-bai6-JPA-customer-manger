package com.codegym.service;

import java.util.List;

public interface IService<B> {
    B findById(Long id);
    List<B> findAll();
    void save(B b);
    void remote(Long id);
}
