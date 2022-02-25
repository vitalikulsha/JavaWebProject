package io.github.vitalikulsha.JavaWebProject.dao.rowmapper;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.dao.constant.Column;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class OrderRowMapper implements RowMapper<Order> {
    private final static DaoFactory factory = DaoFactory.instance();

    @Override
    public Order getEntity(ResultSet resultSet) throws SQLException {
        BookDao bookDao = factory.bookDao();
        UserDao userDao = factory.userDao();
        try {
            int id = resultSet.getInt(Column.ORDER_ID.name());
            int bookId = resultSet.getInt(Column.BOOK_ID.name());
            int userId = resultSet.getInt(Column.USER_ID.name());
            Order.LocationReserve location = Order.LocationReserve.valueOf(resultSet.getString(Column.LOCATION.name()));
            boolean isApproved = resultSet.getBoolean(Column.APPROVED.name());
            Book book = bookDao.getById(bookId);
            User user = userDao.getById(userId);
            return new Order(id, book, user, location, isApproved);
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
    }
}
