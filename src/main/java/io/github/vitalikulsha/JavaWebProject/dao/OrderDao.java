package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> findByUserId(int userId) throws DaoException;

    int updateApproved(boolean approved, int orderId) throws DaoException;

    int updateReserved(ReserveStatus reserveStatus, int orderId) throws DaoException;
}
