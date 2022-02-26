package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.OrderSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.query.constant.sqlquery.SqlQueryFactory;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.*;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private final static OrderSqlQuery orderSqlQuery = SqlQueryFactory.instance().orderSqlQuery();

    public OrderDaoImpl() {
        super(RowMapperFactory.instance().orderRowMapper(),
                orderSqlQuery.FIND_ALL, orderSqlQuery.FIND_BY_ID);
    }




}
