package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;

import java.util.List;

public interface OrderService extends Service<OrderDto> {
    List<OrderDto> getOrdersByUserId(int userId);

    boolean createOrder(int bookId, int userId, ReserveStatus reserveStatus);
}
