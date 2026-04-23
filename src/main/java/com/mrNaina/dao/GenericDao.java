package com.mrNaina.dao;

import java.util.List;

public interface GenericDao<T> {
    void save(T entity);
    T findById(Class<T> clazz, Object id);
    // void update(T entity);
    // void delete(T entity);
    // List<T> findAll(Class<T> clazz);
}
