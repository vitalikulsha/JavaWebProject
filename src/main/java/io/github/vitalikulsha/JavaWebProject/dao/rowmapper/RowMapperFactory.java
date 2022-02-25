package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;


public class RowMapperFactory {
    private final static RowMapperFactory instance = new RowMapperFactory();

    private final static OrderRowMapper orderRowMapper = new OrderRowMapper();
    private final static UserRowMapper userRowMapper = new UserRowMapper();
    private final static BookRowMapper bookRowMapper = new BookRowMapper();

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
}
