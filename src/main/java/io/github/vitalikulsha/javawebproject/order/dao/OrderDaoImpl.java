package io.github.vitalikulsha.javawebproject.order.dao;

import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Table;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.CommonSqlQuery;
import io.github.vitalikulsha.javawebproject.util.dao.rowmapper.RowMapperFactory;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;

import java.util.List;


public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM order_book WHERE user_id=? ORDER BY order_id";
    private static final String SQL_INSERT = "INSERT INTO order_book (book_id, user_id, reserved, approved)" +
            " VALUES ?, ?, ?, ?";
    private static final String SQL_UPDATE = "UPDATE order_book SET book_id=?, user_id=?, reserved=?, approved=?" +
            " WHERE order_id=?";

    public OrderDaoImpl() {
        super(RowMapperFactory.instance().orderRowMapper(),
                new CommonSqlQuery(Table.ORDER_BOOK, Column.ORDER_ID));
    }

    @Override
    public List<Order> findByUserId(int userId) throws DaoException {
        return queryOperator.executeEntityListQuery(SQL_SELECT_BY_USER_ID, userId);
    }

    @Override
    public int save(Order order) throws DaoException {
        return queryOperator.executeUpdate(SQL_INSERT,
                order.getBookId(), order.getUserId(), order.getReserveStatus().name(), order.getApproved());
    }

    @Override
    public int update(Order order) throws DaoException {
        return queryOperator.executeUpdate(SQL_UPDATE, order.getBookId(), order.getUserId(),
                order.getReserveStatus().name(), order.getApproved(), order.getId());
    }
}
