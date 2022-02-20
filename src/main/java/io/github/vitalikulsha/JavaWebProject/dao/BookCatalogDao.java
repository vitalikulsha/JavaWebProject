package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.BookCatalog;

import java.util.List;

public interface BookCatalogDao extends Dao<BookCatalog, Integer>{
    List<BookCatalog> getByAuthorName(String name);

    List<BookCatalog> getByCategoryName(String name);
}
