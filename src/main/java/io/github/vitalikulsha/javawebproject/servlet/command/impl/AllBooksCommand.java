package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.entity.dto.BookDto;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.service.BookService;
import io.github.vitalikulsha.javawebproject.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.Attribute;
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
        Pagination<BookDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        String url = request.getContextPath() + request.getServletPath() + "?";
        request.setAttribute(Attribute.URL, url);
        try {
            List<BookDto> catalog = bookService.getAll();
            pagination.paginate(catalog, request, Attribute.CATALOG);
            return new CommandInfo(Page.ALL_BOOKS, RoutingType.FORWARD);
        } catch (ServiceException e) {
            log.error("Unable to get all books: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }
}
