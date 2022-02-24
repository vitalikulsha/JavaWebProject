package io.github.vitalikulsha.JavaWebProject.dao;

import java.util.List;

public interface Dao<T> {

    T getById(int id);

    List<T> getAll();

    T save(T t);

    void delete(T t);
}

