package io.github.vitalikulsha.JavaWebProject.entity.dto;

public interface DtoConverter<T, E>{

    T toDto(E entity);
}
