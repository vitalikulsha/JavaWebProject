package io.github.vitalikulsha.javawebproject.order.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.CommonSqlQuery;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;

import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final OrderSqlQuery orderSqlQuery = SqlQueryFactory.instance().orderSqlQuery();

    public OrderDaoImpl() {
        super(RowMapperFactory.instance().orderRowMapper(),
                new CommonSqlQuery(Table.ORDER_BOOK, Column.ORDER_ID));
    }

    @Override
    public List<Order> findByUserId(int userId) throws DaoException {
        return queryOperator.executeEntityListQuery(orderSqlQuery.FIND_BY_USER_ID, userId);
    }

    @Override
    public int save(Order order) throws DaoException {
        return queryOperator.executeUpdate(orderSqlQuery.SAVE,
                order.getBookId(), order.getUserId(), order.getReserveStatus().name(), order.getApproved());
    }

    @Override
    public int update(Order order) throws DaoException {
        return queryOperator.executeUpdate(orderSqlQuery.UPDATE, order.getBookId(), order.getUserId(),
                order.getReserveStatus().name(), order.getApproved(), order.getId());
    }

}
