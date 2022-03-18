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
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class CatalogCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        BookService bookService = ServiceFactory.instance().bookService();
        Pagination<BookDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        List<BookDto> catalog;
        //todo: оптимизировать, применить switch с перебором всех параметров
        String url = request.getContextPath() + request.getServletPath() + "?";
        String bookTitle = request.getParameter(Parameter.BOOK_TITLE);
        String authorName = request.getParameter(Parameter.AUTHOR_NAME);
        String categoryName = request.getParameter(Parameter.CATEGORY_NAME);
        String page = request.getParameter(Parameter.PAGE);
        if (bookTitle != null) {
            url = url + Parameter.BOOK_TITLE + "=" + bookTitle;
            catalog = bookService.getBooksByTitle(bookTitle);
        } else if (authorName != null) {
            url = url + Parameter.AUTHOR_NAME + "=" + authorName;
            catalog = bookService.getBooksByAuthorName(authorName);
        } else if (categoryName != null) {
            url = url + Parameter.CATEGORY_NAME + "=" + categoryName;
            catalog = bookService.getBooksByCategoryName(categoryName);
        } else {
            catalog = bookService.getAll();
        }
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        List<Integer> pages = pagination.getPageNumbers(catalog);
        catalog = pagination.getItemsPerPage(catalog, pageNumber);
        request.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.CATALOG, catalog);
        return new CommandInfo(Page.CATALOG, RoutingType.FORWARD);
    }
}
