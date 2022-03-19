package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> findByUserId(int userId);

    int updateApproved(boolean approved, int orderId);

    int updateReserved(ReserveStatus reserveStatus, int orderId);
}
