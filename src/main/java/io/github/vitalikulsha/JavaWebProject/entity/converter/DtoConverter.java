package io.github.vitalikulsha.JavaWebProject.entity.converter;

public interface DtoConverter<T, E>{

    T toDto(E entity);
}
