package io.github.vitalikulsha.JavaWebProject.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, Id> {

    Optional<T> getById(Id id);

    List<T> getAll();

    T save(T t);

    void delete(T t);
}

