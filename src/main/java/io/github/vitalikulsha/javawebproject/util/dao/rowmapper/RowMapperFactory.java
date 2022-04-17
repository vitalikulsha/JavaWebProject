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
    private static final RowMapperFactory instance = new RowMapperFactory();

    private static final OrderRowMapper orderRowMapper = new OrderRowMapper();
    private static final UserRowMapper userRowMapper = new UserRowMapper();
    private static final BookRowMapper bookRowMapper = new BookRowMapper();
    private static final CategoryRowMapper categoryRowMapper = new CategoryRowMapper();
    private static final AuthorRowMapper authorRowMapper = new AuthorRowMapper();

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
