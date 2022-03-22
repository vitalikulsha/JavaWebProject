package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConfigParameter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllBooksCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.instance().bookService();
        Pagination<BookDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        List<BookDto> catalog = bookService.getAll();
        String url = request.getContextPath() + request.getServletPath() + "?";
        request.setAttribute(Attribute.URL, url);
        pagination.paginate(catalog, request, Attribute.CATALOG);
        return new CommandInfo(Page.ALL_BOOKS, RoutingType.FORWARD);
    }
}
