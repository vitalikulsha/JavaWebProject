package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.book.service.BookService;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class AllBooksCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.instance().bookService();
        String url = request.getContextPath() + request.getServletPath() + "?";
        request.setAttribute(SessionAttribute.URL, url);
        String page = request.getParameter(RequestParameter.PAGE);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        try {
            List<BookDTO> catalog = bookService.getAllPagination(pageNumber, ConfigParameter.ITEMS_ON_PAGE);
            setPagesAttribute(bookService.countAll(), request);
            request.setAttribute(SessionAttribute.CATALOG, catalog);
            return new CommandInfo(Page.ALL_BOOKS, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get all books: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }

    private void setPagesAttribute(int count, HttpServletRequest request) {
        int numPage = (count / ConfigParameter.ITEMS_ON_PAGE)
                + (count % ConfigParameter.ITEMS_ON_PAGE == 0 ? 0 : 1);
        List<Integer> pages = IntStream.range(1, numPage + 1)
                .boxed()
                .collect(Collectors.toList());
        request.setAttribute(SessionAttribute.PAGES, pages);
    }
}
