package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.model.Category;

import java.util.Optional;

public interface CategoryDao extends Dao<Category, Integer>{
    Optional<Category> getByName(String name);
}
