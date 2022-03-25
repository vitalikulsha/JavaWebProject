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
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CatalogCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        BookService bookService = ServiceFactory.instance().bookService();
        Pagination<BookDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        List<BookDto> catalog;
        String url = request.getContextPath() + request.getServletPath() + "?";
        String bookTitle = request.getParameter(Parameter.BOOK_TITLE);
        String authorName = request.getParameter(Parameter.AUTHOR_NAME);
        String categoryName = request.getParameter(Parameter.CATEGORY_NAME);
// todo: вывести URL в отдельный метод и доработать getCatalog()

        if (bookTitle != null) {
            url = url + Parameter.BOOK_TITLE + "=" + bookTitle;
            try {
                catalog = removeQuantityBooksZero(bookService.getBooksByTitle(bookTitle));
            } catch (ServiceException e) {
                log.error("Unable to get books by title: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else if (authorName != null) {
            url = url + Parameter.AUTHOR_NAME + "=" + authorName;
            try {
                catalog = removeQuantityBooksZero(bookService.getBooksByAuthorName(authorName));
            } catch (ServiceException e) {
                log.error("Unable to get books by author name: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else if (categoryName != null) {
            url = url + Parameter.CATEGORY_NAME + "=" + categoryName;
            try {
                catalog = removeQuantityBooksZero(bookService.getBooksByCategoryName(categoryName));
            } catch (ServiceException e) {
                log.error("Unable to get books by category name: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        } else {
            try {
                catalog = removeQuantityBooksZero(bookService.getAll());
            } catch (ServiceException e) {
                log.error("Unable to get all books: " + e.getMessage());
                return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
            }
        }

            if (catalog.isEmpty()) {
                session.setAttribute(Attribute.BOOK_FOUND, false);
                return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
            } else {
                log.info("url: " + url);
                request.setAttribute(Attribute.URL, url);
                session.setAttribute(Attribute.BOOK_FOUND, true);
                pagination.paginate(catalog, request, Attribute.CATALOG);
                return new CommandInfo(Page.CATALOG, RoutingType.FORWARD);
            }

    }

    private List<BookDto> getCatalog(HttpServletRequest request, String url) throws ServiceException {
        BookService bookService = ServiceFactory.instance().bookService();
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove(Parameter.PAGE);
        List<BookDto> catalog = new ArrayList<>();
        if (params.isEmpty()) {
            catalog = removeQuantityBooksZero(bookService.getAll());
        } else {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                String param = entry.getKey();
                String value = entry.getValue()[0];
                url = url + param + "=" + entry.getValue()[0];
                log.info("url: " + url);
                switch (param) {
                    case (Parameter.BOOK_TITLE):
                        log.info("param: " + param);
                        catalog = removeQuantityBooksZero(bookService.getBooksByTitle(value));
                        break;
                    case (Parameter.AUTHOR_NAME):
                        log.info("param: " + param);
                        catalog = removeQuantityBooksZero(bookService.getBooksByAuthorName(value));
                        break;
                    case (Parameter.CATEGORY_NAME):
                        log.info("param: " + param);
                        catalog = removeQuantityBooksZero((bookService.getBooksByCategoryName(value)));
                        break;
                    default:
                        log.info("param: " + param);
                        catalog = removeQuantityBooksZero(bookService.getAll());
                }
            }
        }
        log.info("catalog size: " + catalog.size());
        return catalog;
    }

    private List<BookDto> removeQuantityBooksZero(List<BookDto> books) {
        return books.stream()
                .filter(b -> b.getNumber() != 0)
                .collect(Collectors.toList());
    }
}
