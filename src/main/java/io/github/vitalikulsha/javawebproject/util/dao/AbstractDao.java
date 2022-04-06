package io.github.vitalikulsha.javawebproject.util.dao;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.category.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.QueryOperator;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapper;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.order.dao.OrderDao;
import io.github.vitalikulsha.javawebproject.user.dao.UserDao;

import java.util.List;

/**
 * Abstract DAO class
 * See also:
 * {@link Dao}
 * {@link AuthorDao}
 * {@link BookDao}
 * {@link CategoryDao}
 * {@link OrderDao}
 * {@link UserDao}
 *
 * @param <T> element/entity type in this AbstractDAO
 */
public abstract class AbstractDao<T> implements Dao<T> {
    protected final String FIND_ALL_QUERY;
    protected final String FIND_BY_ID_QUERY;
    protected final String DELETE_BY_ID;

    protected final RowMapper<T> mapper;
    protected final QueryOperator<T> queryOperator;

    public AbstractDao(RowMapper<T> mapper, String findAllQuery, String findById, String deleteById) {
        this.mapper = mapper;
        this.queryOperator = new QueryOperator<>(mapper);
        this.FIND_ALL_QUERY = findAllQuery;
        this.FIND_BY_ID_QUERY = findById;
        this.DELETE_BY_ID = deleteById;
    }

    @Override
    public T findById(int id) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_ID_QUERY, id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        return queryOperator.executeEntityListQueryWithoutParam(FIND_ALL_QUERY);
    }

    @Override
    public int save(T t) throws DaoException {
        return 0;
    }

    @Override
    public int deleteById(int id) throws DaoException {
        return queryOperator.executeUpdate(DELETE_BY_ID, id);
    }

    @Override
    public int update(T t) throws DaoException {
        return 0;
    }
}
