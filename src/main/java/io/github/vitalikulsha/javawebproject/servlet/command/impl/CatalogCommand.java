package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.book.service.BookService;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.servlet.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
public class CatalogCommand implements Command {

    @Override
    public CommandInfo execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.BOOK_EXISTS);
        String url = buildUrl(request);
        try {
            List<BookDTO> catalog = getCatalog(request);
            log.info("url: " + url + ", catalog size: " + catalog.size());
            if (catalog.isEmpty()) {
                session.setAttribute(SessionAttribute.BOOK_FOUND, false);
                return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
            } else {
                request.setAttribute(SessionAttribute.URL, url);
                session.setAttribute(SessionAttribute.BOOK_FOUND, true);
                request.setAttribute(SessionAttribute.CATALOG, catalog);
                return new CommandInfo(Page.CATALOG, RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            log.error("Unable to get book catalog: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }

    private String buildUrl(HttpServletRequest request) {
        StringBuilder url = new StringBuilder(request.getContextPath() + request.getServletPath() + "?");
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove(RequestParameter.PAGE);
        if (!params.isEmpty()) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                url.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue()[0]);
            }
        }
        return url.toString();
    }

    private List<BookDTO> getCatalog(HttpServletRequest request) throws ServiceException {
        BookService bookService = ServiceFactory.instance().bookService();
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove(RequestParameter.PAGE);
        String page = request.getParameter(RequestParameter.PAGE);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        Pagination pagination = new Pagination(pageNumber, ConfigParameter.ITEMS_ON_PAGE);
        List<Integer> pages = new ArrayList<>();
        List<BookDTO> books = new ArrayList<>();
        if (!params.isEmpty()) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                String param = entry.getKey();
                String paramValue = entry.getValue()[0];
                log.info("param: " + param);
                switch (param) {
                    case (RequestParameter.BOOK_TITLE):
                        pages = pagination.getPages(bookService.countBySearchParam(Column.TITLE, paramValue));
                        books = bookService.getBooksByTitle(pagination, paramValue);
                        break;
                    case (RequestParameter.AUTHOR_NAME):
                        pages = pagination.getPages(bookService.countBySearchParam(Column.LASTNAME, paramValue));
                        books = bookService.getBooksByAuthorName(pagination, paramValue);
                        break;
                    case (RequestParameter.CATEGORY_NAME):
                        pages = pagination.getPages(bookService.countBySearchParam(Column.NAME, paramValue));
                        books = bookService.getBooksByCategoryName(pagination, paramValue);
                        break;
                }
            }
        } else {
            pages = pagination.getPages(bookService.countAll());
            books = bookService.getAll(pagination);
        }
        request.setAttribute(SessionAttribute.PAGES, pages);
        return books;
    }
}
