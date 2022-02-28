package io.github.vitalikulsha.JavaWebProject.dto;

import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;

public interface DtoConverter<T, E>{

    T toDto(E entity);
}
