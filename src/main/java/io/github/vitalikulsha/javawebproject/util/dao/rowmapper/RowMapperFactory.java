package io.github.vitalikulsha.javawebproject.util.dao.rowmapper;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorRowMapper;
import io.github.vitalikulsha.javawebproject.book.dao.BookRowMapper;
import io.github.vitalikulsha.javawebproject.category.dao.CategoryRowMapper;
import io.github.vitalikulsha.javawebproject.order.dao.OrderRowMapper;
import io.github.vitalikulsha.javawebproject.user.dao.UserRowMapper;

/**
 * Factory, that provides row mappers.
 */
public class RowMapperFactory {
    private final static RowMapperFactory instance = new RowMapperFactory();

    private final static OrderRowMapper orderRowMapper = new OrderRowMapper();
    private final static UserRowMapper userRowMapper = new UserRowMapper();
    private final static BookRowMapper bookRowMapper = new BookRowMapper();
    private final static CategoryRowMapper categoryRowMapper = new CategoryRowMapper();
    private final static AuthorRowMapper authorRowMapper = new AuthorRowMapper();

    private RowMapperFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of RowMapperFactory
     */
    public static RowMapperFactory instance() {
        return instance;
    }

    public OrderRowMapper orderRowMapper() {
        return orderRowMapper;
    }


    public UserRowMapper userRowMapper() {
        return userRowMapper;
    }

    public BookRowMapper bookRowMapper() {
        return bookRowMapper;
    }

    public CategoryRowMapper categoryRowMapper() {
        return categoryRowMapper;
    }

    public AuthorRowMapper authorRowMapper() {
        return authorRowMapper;
    }
}
