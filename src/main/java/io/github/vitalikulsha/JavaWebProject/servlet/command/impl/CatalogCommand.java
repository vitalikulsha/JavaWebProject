package io.github.vitalikulsha.JavaWebProject.servlet.command.impl;

import io.github.vitalikulsha.JavaWebProject.config.ConfigParameter;
import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.servlet.command.Command;
import io.github.vitalikulsha.JavaWebProject.servlet.command.CommandInfo;
import io.github.vitalikulsha.JavaWebProject.servlet.command.RoutingType;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CatalogCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        BookService bookService = ServiceFactory.instance().bookService();
        Pagination<BookDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        List<BookDto> catalog;
        //todo: оптимизировать, применить switch с перебором всех параметров
        String url = request.getContextPath() + request.getServletPath() + "?";
        String bookTitle = request.getParameter(Parameter.BOOK_TITLE);
        String authorName = request.getParameter(Parameter.AUTHOR_NAME);
        String categoryName = request.getParameter(Parameter.CATEGORY_NAME);
        if (bookTitle != null) {
            url = url + Parameter.BOOK_TITLE + "=" + bookTitle;
            try {
                catalog = removeQuantityBooksZero(bookService.getBooksByTitle(bookTitle));
            } catch (ServiceException e) {
                log.error("Unable to get books by title.", e);
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else if (authorName != null) {
            url = url + Parameter.AUTHOR_NAME + "=" + authorName;
            try {
                catalog = removeQuantityBooksZero(bookService.getBooksByAuthorName(authorName));
            } catch (ServiceException e) {
                log.error("Unable to get books by author name.", e);
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else if (categoryName != null) {
            url = url + Parameter.CATEGORY_NAME + "=" + categoryName;
            try {
                catalog = removeQuantityBooksZero(bookService.getBooksByCategoryName(categoryName));
            } catch (ServiceException e) {
                log.error("Unable to get books by category name.", e);
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else {
            try {
                catalog = removeQuantityBooksZero(bookService.getAll());
            } catch (ServiceException e) {
                log.error("Unable to get all books.");
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }
        if (catalog.isEmpty()) {
            session.setAttribute(Attribute.BOOK_FOUND, false);
            return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
        } else {
            request.setAttribute(Attribute.URL, url);
            session.setAttribute(Attribute.BOOK_FOUND, true);
            pagination.paginate(catalog, request, Attribute.CATALOG);
            return new CommandInfo(Page.CATALOG, RoutingType.FORWARD);
        }
    }

    private List<BookDto> removeQuantityBooksZero(List<BookDto> books) {
        return books.stream()
                .filter(b -> b.getNumber() != 0)
                .collect(Collectors.toList());
    }
}
