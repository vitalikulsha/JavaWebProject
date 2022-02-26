package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;


public class RowMapperFactory {
    private final static RowMapperFactory instance = new RowMapperFactory();

    private final static OrderRowMapper orderRowMapper = new OrderRowMapper();
    private final static UserRowMapper userRowMapper = new UserRowMapper();
    private final static BookRowMapper bookRowMapper = new BookRowMapper();
    private final static CategoryRowMapper categoryRowMapper = new CategoryRowMapper();
    private final static AuthorRowMapper authorRowMapper = new AuthorRowMapper();

    private RowMapperFactory() {
    }

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
