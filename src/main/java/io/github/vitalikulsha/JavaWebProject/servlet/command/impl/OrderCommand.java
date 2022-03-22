package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.entity.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.OrderService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.constant.Value;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class OrderCommand implements Command {

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
        BookService bookService = ServiceFactory.instance().bookService();
        int bookId = Integer.parseInt(request.getParameter(Parameter.BOOK_ID));
        BookDto bookDto = bookService.getById(bookId);
        if (bookDto == null) {
            session.setAttribute(Attribute.BOOK_FOUND, false);
            return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
        } else {
            session.setAttribute(Attribute.BOOK_FOUND, true);
            session.setAttribute(Attribute.BOOK, bookDto);
            return new CommandInfo(Page.ORDER, RoutingType.FORWARD);
        }
    }

    private CommandInfo getCommandInfoPost(HttpServletRequest request, HttpSession session) {
        OrderService orderService = ServiceFactory.instance().orderService();
        BookService bookService = ServiceFactory.instance().bookService();
        ReserveStatus reserveStatus = ReserveStatus.valueOf(request.getParameter(Parameter.RESERVE_STATUS));
        BookDto bookDto = (BookDto) session.getAttribute(Attribute.BOOK);
        UserDto user = (UserDto) session.getAttribute(Attribute.USER);
        if (!isBookExists(orderService, bookDto, user)) {
            orderService.createOrder(bookDto.getId(), user.getId(), reserveStatus);
            bookService.removeOneBook(bookDto.getId());
        } else {
            log.info("The book id = " + bookDto.getId() + " is already in the list of orders.");
            request.setAttribute(Attribute.IS_EXISTS, true);
            return new CommandInfo(Page.BOOK_SEARCH, RoutingType.FORWARD);
        }
        return new CommandInfo(UserPath.READER_ORDERS.getPath(), RoutingType.REDIRECT);
    }

    private boolean isBookExists(OrderService orderService, BookDto bookDto, UserDto user) {
        return orderService.getOrdersByUserId(user.getId())
                .stream()
                .map(o -> o.getBookDto().getId())
                .anyMatch(id -> id == bookDto.getId());
    }
}
