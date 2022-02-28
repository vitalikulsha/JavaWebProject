package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.query.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order getEntity(ResultSet resultSet) throws SQLException {
        try {
            int id = resultSet.getInt(Column.ORDER_ID.name());
            int bookId = resultSet.getInt(Column.BOOK_ID.name());
            int userId = resultSet.getInt(Column.USER_ID.name());
            ReserveStatus reserveStatus = ReserveStatus.valueOf(resultSet.getString(Column.RESERVED.name()));
            boolean approved = resultSet.getBoolean(Column.APPROVED.name());
            return new Order(id, bookId, userId, reserveStatus, approved);
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
    }
}
