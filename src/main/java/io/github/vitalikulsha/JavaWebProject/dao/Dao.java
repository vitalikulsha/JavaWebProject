package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;

public interface Dao<T> {

    T findById(int id) throws DaoException;

    List<T> findAll() throws DaoException;

    int save(T t) throws DaoException;

    int deleteById(int id) throws DaoException;
}

