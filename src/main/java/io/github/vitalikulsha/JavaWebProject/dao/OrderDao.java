package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> findByUserId(int userId);
}
