package com.javaspring.team2.project.smdb.service;

import java.util.List;

public interface BaseService<T, ID> {
    T create(T entity);

    List<T> createAll(T... entities);

    List<T> createAll(List<T> entities);

    void update(T entity);

    void delete(T entity);

    void deleteById(ID id);

    boolean exists(T entity);

    List<T> findAll();

    T find(Long id);

    T get(ID id);
}
