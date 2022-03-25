package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;

public interface AuthorDao extends Dao<Author>{
    List<Author> findAuthorsByBookId(int bookId) throws DaoException;
}
