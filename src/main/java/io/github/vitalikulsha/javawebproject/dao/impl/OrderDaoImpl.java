package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.dao.*;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.OrderSqlQuery;
import io.github.vitalikulsha.javawebproject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.javawebproject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.entity.*;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private final static OrderSqlQuery orderSqlQuery = SqlQueryFactory.instance().orderSqlQuery();

    public OrderDaoImpl() {
        super(RowMapperFactory.instance().orderRowMapper(),
                orderSqlQuery.FIND_ALL, orderSqlQuery.FIND_BY_ID, orderSqlQuery.DELETE_BY_ID);
    }

    @Override
    public List<Order> findByUserId(int userId) throws DaoException {
        return queryOperator.executeEntityListQueryWithParam(orderSqlQuery.FIND_BY_USER_ID, userId);
    }

    @Override
    public int save(Order order) throws DaoException {
        return queryOperator.executeUpdate(orderSqlQuery.SAVE,
                order.getBookId(), order.getUserId(), order.getReserveStatus().name(), order.getApproved());
    }

    @Override
    public int updateApproved(boolean approved, int orderId) throws DaoException {
        return queryOperator.executeUpdate(orderSqlQuery.UPDATE_APPROVAL, approved, orderId);
    }

    @Override
    public int updateReserved(ReserveStatus reserveStatus, int orderId) throws DaoException {
        return queryOperator.executeUpdate(orderSqlQuery.UPDATE_RESERVED, reserveStatus.name(), orderId);
    }

}
