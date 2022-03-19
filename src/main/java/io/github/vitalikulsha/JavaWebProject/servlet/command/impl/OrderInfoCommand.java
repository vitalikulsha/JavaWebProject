package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.service.BookService;
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
        if (method.equals(Value.GET)) {
            return getCommandInfoGet(request, session);
        } else if (method.equals(Value.POST)) {
            return getCommandInfoPost(request, session);
        }
        return null;
    }

    private CommandInfo getCommandInfoGet(HttpServletRequest request, HttpSession session) {
        OrderService orderService = ServiceFactory.instance().orderService();
        int orderId = Integer.parseInt(request.getParameter(Parameter.ORDER_ID));
        OrderDto orderDto = orderService.getById(orderId);
        session.setAttribute(Attribute.ORDER, orderDto);
        return new CommandInfo(Page.ORDER_INFO, RoutingType.FORWARD);
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request, HttpSession session) {
        BookService bookService = ServiceFactory.instance().bookService();
        OrderService orderService = ServiceFactory.instance().orderService();
        OrderDto orderDto = (OrderDto) session.getAttribute(Attribute.ORDER);
        log.info("Order from session: " + orderDto);
        String action = request.getParameter(Parameter.ACTION);
        if (action.equals(Value.APPROVE)) {
            orderService.updateOrderApproval(true, orderDto.getId());
            bookService.removeOneBook(orderDto.getBookDto().getId());
        } else if (action.equals(Value.CANCEL)) {
            orderService.deleteById(orderDto.getId());
            bookService.addOneBook(orderDto.getBookDto().getId());
            return new CommandInfo(AdminPath.ALL_ORDERS.getPath(), RoutingType.REDIRECT);
        }
        orderDto = orderService.getById(orderDto.getId());
        session.setAttribute(Attribute.ORDER, orderDto);
        request.setAttribute(Parameter.ORDER_ID, orderDto.getId());
        return new CommandInfo(Page.ORDER_INFO, RoutingType.FORWARD);
    }
}
