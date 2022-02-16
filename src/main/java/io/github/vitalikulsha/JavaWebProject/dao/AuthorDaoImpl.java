package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.model.Author;
import io.github.vitalikulsha.JavaWebProject.util.ConnectionSource;

import java.util.List;
import java.util.Optional;

public class AuthorDaoImpl implements AuthorDao{
    private final ConnectionSource connectionSource = ConnectionSource.instance();

    @Override
    public Optional<Author> getById(Integer Id) {
        return Optional.empty();
    }

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public void delete(Author author) {

    }
}
