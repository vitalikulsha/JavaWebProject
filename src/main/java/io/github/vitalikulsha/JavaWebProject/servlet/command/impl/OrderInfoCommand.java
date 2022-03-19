package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.constant.Value;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class OrderInfoCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String method = request.getMethod();
        OrderService orderService = ServiceFactory.instance().orderService();
        if (method.equals(Value.GET)) {
            int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
            OrderDto orderDto = orderService.getById(orderId);
            session.setAttribute(Attribute.ORDER, orderDto);
            return new CommandInfo(Page.ORDER_INFO, RoutingType.FORWARD);
        } else if (method.equals(Value.POST)) {
            OrderDto orderDto = (OrderDto) session.getAttribute(Attribute.ORDER);
            log.info("Order from session: " + orderDto);
            String action = request.getParameter(Parameter.ACTION);
            if (action.equals(Value.APPROVE)) {
                orderService.updateApprovalOrder(true, orderDto.getId());
            }
            orderDto = orderService.getById(orderDto.getId());
            session.setAttribute(Attribute.ORDER, orderDto);
            request.setAttribute(Parameter.ORDER_ID, orderDto.getId());
            return new CommandInfo(Page.ORDER_INFO, RoutingType.FORWARD);
        }
        return null;
    }
}
