package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.order.entity.OrderDTO;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.order.service.OrderService;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.user.service.UserService;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.path.AdminPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class ReaderInfoCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.instance().userService();
        OrderService orderService = ServiceFactory.instance().orderService();
        Pagination<OrderDTO> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        int readerId = Integer.parseInt(request.getParameter(RequestParameter.READER_ID));
        String url = request.getServletContext().getContextPath()
                + AdminPath.READER_INFO.getPath()
                + "?" + RequestParameter.READER_ID + "=" + readerId;
        request.setAttribute(SessionAttribute.URL, url);
        try {
            UserDTO reader = userService.getById(readerId);
            List<OrderDTO> readerOrders = orderService.getOrdersByUserId(reader.getId());
            request.setAttribute(SessionAttribute.READER, reader);
            pagination.paginate(readerOrders, request, SessionAttribute.READER_ORDERS);
            return new CommandInfo(Page.READER_INFO, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get user information: " +  e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
