package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.book.service.BookService;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class AllBooksCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.instance().bookService();
        String url = request.getContextPath() + request.getServletPath() + "?";
        request.setAttribute(SessionAttribute.URL, url);
        String page = request.getParameter(RequestParameter.PAGE);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        Pagination pagination = new Pagination(pageNumber);
        try {
            List<BookDTO> catalog = bookService.getAll(pagination);
            List<Integer> pages = ConfigParameter.getPages(bookService.countAll());
            request.setAttribute(SessionAttribute.CATALOG, catalog);
            request.setAttribute(SessionAttribute.PAGES, pages);
            return new CommandInfo(Page.ALL_BOOKS, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get all books: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
