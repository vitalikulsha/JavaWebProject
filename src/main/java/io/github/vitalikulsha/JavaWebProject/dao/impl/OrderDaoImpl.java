package io.github.vitalikulsha.JavaWebProject.dao.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConnectionSource;
import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dao.OrderDao;
import io.github.vitalikulsha.JavaWebProject.dao.UserDao;
import io.github.vitalikulsha.JavaWebProject.entity.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderDaoImpl implements OrderDao {
    private final ConnectionSource connectionSource = ConnectionSource.instance();
    private final DaoFactory factory = new DaoFactory();

    @Override
    public Order getById(Integer id) {
        String sqlQuery = "SELECT * FROM order_book o_b" +
                " INNER JOIN book ON o_b.book_id=book.book_id" +
                " INNER JOIN user ON o_b.user_id=user.user_id" +
                " WHERE order_id=?";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sqlQuery = "SELECT * FROM order_book o_b" +
                " INNER JOIN book ON o_b.book_id=book.book_id" +
                " INNER JOIN user ON o_b.user_id=user.user_id";
        try (Connection connection = connectionSource.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                orders.add(getOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public void delete(Order order) {

    }


    private Order getOrder(ResultSet resultSet) {
        BookDao bookDao = factory.bookDao();
        UserDao userDao = factory.userDao();
        try {
            int id = resultSet.getInt("order_id");
            int bookId = resultSet.getInt("book_id");
            int userId = resultSet.getInt("user_id");
            Order.LocationReserve locationReserve = Order.LocationReserve.valueOf(resultSet.getString("location"));
            boolean isApproved = resultSet.getBoolean("approved");
            Book book = bookDao.getById(bookId);
            User user = userDao.getById(userId);
            return new Order(id, book, user, locationReserve, isApproved);
        } catch (SQLException e) {
            log.error(e.toString());
            return null;
        }
    }


}
