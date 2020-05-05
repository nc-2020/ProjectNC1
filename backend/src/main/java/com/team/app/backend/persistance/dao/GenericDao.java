package com.team.app.backend.persistance.dao;

public interface GenericDao<T> {

    T save(T obj);

    T update(T obj);

    T get(Long id);

    void delete(Long id);

}
