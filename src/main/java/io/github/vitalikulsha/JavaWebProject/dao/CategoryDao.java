package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.Category;

import java.util.List;

public interface CategoryDao extends Dao<Category, Integer>{
    List<Category> getByName(String name);
}
