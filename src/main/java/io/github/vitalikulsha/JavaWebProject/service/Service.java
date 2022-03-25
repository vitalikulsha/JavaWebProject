package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;

import java.util.List;

public interface Service<T> {
    T getById(int id) throws ServiceException;

    List<T> getAll() throws ServiceException;

    boolean deleteById(int id) throws ServiceException;
}
