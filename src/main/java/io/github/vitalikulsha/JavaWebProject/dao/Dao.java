package io.github.vitalikulsha.JavaWebProject.dao;

import java.util.List;

public interface Dao<T> {

    T findById(int id);

    List<T> findAll();

    int save(T t);

    int deleteById(int id);
}

