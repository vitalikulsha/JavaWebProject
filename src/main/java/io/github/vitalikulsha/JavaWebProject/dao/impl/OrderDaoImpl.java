package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.OrderSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.*;

import java.util.List;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private final static OrderSqlQuery orderSqlQuery = SqlQueryFactory.instance().orderSqlQuery();

    public OrderDaoImpl() {
        super(RowMapperFactory.instance().orderRowMapper(),
                orderSqlQuery.FIND_ALL, orderSqlQuery.FIND_BY_ID, orderSqlQuery.DELETE_BY_ID);
    }

    @Override
    public List<Order> findByUserId(int userId) {
        return queryOperator.executeEntityListQueryWithParam(orderSqlQuery.FIND_BY_USER_ID, userId);
    }

    @Override
    public int save(Order order) {
        return queryOperator.executeUpdate(orderSqlQuery.SAVE,
                order.getBookId(), order.getUserId(), order.getReserveStatus().name(), order.getApproved());
    }

    @Override
    public int updateApproved(boolean approved, int orderId){
        return queryOperator.executeUpdate(orderSqlQuery.UPDATE_APPROVAL, approved, orderId);
    }

    @Override
    public int updateReserved(ReserveStatus reserveStatus, int orderId){
        return queryOperator.executeUpdate(orderSqlQuery.UPDATE_RESERVED, reserveStatus.name(), orderId);
    }

}
