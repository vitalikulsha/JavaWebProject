package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.dao.*;
import io.github.vitalikulsha.JavaWebProject.dao.constant.sqlquery.OrderSqlQuery;
import io.github.vitalikulsha.JavaWebProject.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.JavaWebProject.entity.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(RowMapperFactory.instance().orderRowMapper(),
                OrderSqlQuery.FIND_ALL, OrderSqlQuery.FIND_BY_ID);
    }


}
