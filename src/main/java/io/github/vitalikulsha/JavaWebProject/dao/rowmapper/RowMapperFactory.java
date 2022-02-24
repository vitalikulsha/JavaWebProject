package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;


import io.github.vitalikulsha.JavaWebProject.entity.Order;

public class RowMapperFactory {
    private final static RowMapperFactory instance = new RowMapperFactory();

    private final static OrderRowMapper orderRowMapper = new OrderRowMapper();
    private final static UserRowMapper userRowMapper = new UserRowMapper();

    private RowMapperFactory() {
    }

    public static RowMapperFactory getInstance() {
        return instance;
    }

    public OrderRowMapper getOrderRowMapper() {
        return orderRowMapper;
    }

    public UserRowMapper getUserRowMapper(){
        return userRowMapper;
    }
}
