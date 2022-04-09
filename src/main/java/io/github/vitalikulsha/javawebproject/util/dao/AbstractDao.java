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
    public final String FIND_ALL;
    public final String FIND_ALL_PAGE;
    public final String FIND_BY_ID;
    public final String DELETE_BY_ID;
    public final String COUNT_ALL;

    public final RowMapper<T> mapper;
    public final QueryOperator<T> queryOperator;

    public AbstractDao(RowMapper<T> mapper, String findAll, String findAllPage, String findById, String deleteById, String countAll) {
        this.mapper = mapper;
        this.queryOperator = new QueryOperator<>(mapper);
        this.FIND_ALL = findAll;
        this.FIND_ALL_PAGE = findAllPage;
        this.FIND_BY_ID = findById;
        this.DELETE_BY_ID = deleteById;
        this.COUNT_ALL = countAll;
    }

    @Override
    public T findById(int id) throws DaoException {
        return queryOperator.executeSingleEntityQuery(FIND_BY_ID, id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        return queryOperator.executeEntityListQuery(FIND_ALL);
    }

    @Override
    public List<T> findAll(int firstIndex, int itemsOnPage) throws DaoException{
        return queryOperator.executeEntityListQuery(FIND_ALL_PAGE, firstIndex, itemsOnPage);
    }

    @Override
    public int deleteById(int id) throws DaoException {
        return queryOperator.executeUpdate(DELETE_BY_ID, id);
    }

    @Override
    public int countAll() throws DaoException {
        return queryOperator.executeCountQuery(COUNT_ALL);
    }

    @Override
    public int save(T t) throws DaoException {
        return 0;
    }

    @Override
    public int update(T t) throws DaoException {
        return 0;
    }
}
