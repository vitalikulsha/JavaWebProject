package io.github.vitalikulsha.javawebproject.util.dao;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.category.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.QueryOperator;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.CommonSqlQuery;
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
    public final QueryOperator<T> queryOperator;
    public final CommonSqlQuery commonSqlQuery;

    protected AbstractDao(RowMapper<T> mapper, CommonSqlQuery commonSqlQuery){
        this.queryOperator = new QueryOperator<>(mapper);
        this.commonSqlQuery = commonSqlQuery;
    }

    @Override
    public T findById(int id) throws DaoException {
        return queryOperator.executeSingleEntityQuery(commonSqlQuery.SQL_SELECT_BY_ID, id);
    }

    @Override
    public List<T> findAll() throws DaoException {
        return queryOperator.executeEntityListQuery(commonSqlQuery.SQL_SELECT_ALL);
    }

    @Override
    public List<T> findAll(int fromIndex, int itemsOnPage) throws DaoException{
        return queryOperator.executeEntityListQuery(commonSqlQuery.SQL_SELECT_ALL_PAGE, fromIndex, itemsOnPage);
    }

    @Override
    public int deleteById(int id) throws DaoException {
        return queryOperator.executeUpdate(commonSqlQuery.SQL_DELETE_BY_ID, id);
    }

    @Override
    public int countAll() throws DaoException {
        return queryOperator.executeCountQuery(commonSqlQuery.SQL_SELECT_COUNT_ALL);
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
