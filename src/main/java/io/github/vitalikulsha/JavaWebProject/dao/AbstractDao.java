package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.dao.query.QueryOperator;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapper;

import java.util.List;

public abstract class AbstractDao<T> implements Dao<T>{
    protected final String FIND_ALL_QUERY;
    protected final String FIND_BY_ID_QUERY;

    protected final RowMapper<T> mapper;
    protected final QueryOperator<T> queryOperator;

    public AbstractDao(RowMapper<T> mapper, String findAllQuery, String findById) {
        this.mapper = mapper;
        this.queryOperator = new QueryOperator<>(mapper);
        this.FIND_ALL_QUERY = findAllQuery;
        this.FIND_BY_ID_QUERY = findById;
    }

    @Override
    public T getById(int id) {
        return queryOperator.executeSingleEntityQuery(FIND_BY_ID_QUERY, id);
    }

    @Override
    public List<T> getAll() {
        return queryOperator.executeEntityListQueryWithoutParam(FIND_ALL_QUERY);
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public void delete(T t) {

    }
}
