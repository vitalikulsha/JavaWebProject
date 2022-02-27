package io.github.vitalikulsha.JavaWebProject.dao;

import java.util.List;

public interface Dao<T> {

    T findById(int id);

    List<T> findAll();

    T save(T t);

    void delete(T t);
}

