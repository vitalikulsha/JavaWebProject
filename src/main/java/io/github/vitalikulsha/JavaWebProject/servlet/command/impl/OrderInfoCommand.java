package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderInfoCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = ServiceFactory.instance().orderService();
        int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
        OrderDto orderDto = orderService.getById(orderId);
        request.setAttribute(Attribute.ORDER, orderDto);
        return new CommandInfo(Page.ORDER_INFO, RoutingType.FORWARD);
    }
}
