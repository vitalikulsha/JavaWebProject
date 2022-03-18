package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConfigParameter;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReaderInfoCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = ServiceFactory.instance().userService();
        OrderService orderService = ServiceFactory.instance().orderService();
        Pagination<OrderDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        int readerId = Integer.parseInt(request.getParameter(Parameter.READER_ID));
        String page = request.getParameter(Parameter.PAGE);
        String url = request.getServletContext().getContextPath()
                + AdminPath.READER_INFO.getPath()
                + "?" + Parameter.READER_ID + "=" + readerId;
        UserDto reader = userService.getById(readerId);
        List<OrderDto> readerOrders = orderService.getOrdersByUserId(reader.getId());
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        List<Integer> pages = pagination.getPageNumbers(readerOrders);
        readerOrders = pagination.getItemsPerPage(readerOrders, pageNumber);
        request.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.READER_ORDERS, readerOrders);
        request.setAttribute(Attribute.READER, reader);
        return new CommandInfo(Page.READER_INFO, RoutingType.FORWARD);
    }
}
