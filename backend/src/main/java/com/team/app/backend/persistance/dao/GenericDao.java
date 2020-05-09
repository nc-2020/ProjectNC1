package com.team.app.backend.persistance.dao;

/**
 *
 * @param <T> entity
 * @param <D> DTO object
 */
public interface GenericDao<T, D> {

    T save(D obj);

    T update(D obj);

    T get(Long id);

    void delete(Long id);

}
