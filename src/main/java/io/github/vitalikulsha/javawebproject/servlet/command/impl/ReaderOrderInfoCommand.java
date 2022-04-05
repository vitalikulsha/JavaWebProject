package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.order.entity.OrderDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.order.service.OrderService;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.Attribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.constant.Parameter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ReaderOrderInfoCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.instance().orderService();
        int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
        try {
            OrderDTO order = orderService.getById(orderId);
            request.setAttribute(Attribute.ORDER, order);
            return new CommandInfo(Page.READER_ORDER_INFO, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get order by id: "  + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
