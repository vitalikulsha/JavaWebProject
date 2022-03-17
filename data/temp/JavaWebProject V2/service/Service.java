package io.github.vitalikulsha.JavaWebProject.service;

import java.util.List;

public interface Service<T> {
    T getById(int id);

    List<T> getAll();

    T save(T t);

    void delete(T t);
}
