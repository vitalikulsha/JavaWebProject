package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.book.service.BookService;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private void setPagesAttribute(int count, HttpServletRequest request) {
        int numPage = (count / ConfigParameter.ITEMS_ON_PAGE)
                + (count % ConfigParameter.ITEMS_ON_PAGE == 0 ? 0 : 1);
        List<Integer> pages = IntStream.range(1, numPage + 1)
                .boxed()
                .collect(Collectors.toList());
        request.setAttribute(SessionAttribute.PAGES, pages);
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
        if (!params.isEmpty()) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                String param = entry.getKey();
                String paramValue = entry.getValue()[0];
                log.info("param: " + param);
                switch (param) {
                    case (RequestParameter.BOOK_TITLE):
                        setPagesAttribute(bookService.countBySearchParam(Column.TITLE, paramValue), request);
                        return bookService.getBooksByTitle(pageNumber, ConfigParameter.ITEMS_ON_PAGE, paramValue);
                    case (RequestParameter.AUTHOR_NAME):
                        setPagesAttribute(bookService.countBySearchParam(Column.LASTNAME, paramValue), request);
                        return bookService.getBooksByAuthorName(pageNumber, ConfigParameter.ITEMS_ON_PAGE, paramValue);
                    case (RequestParameter.CATEGORY_NAME):
                        setPagesAttribute(bookService.countBySearchParam(Column.NAME, paramValue), request);
                        return bookService.getBooksByCategoryName(pageNumber, ConfigParameter.ITEMS_ON_PAGE, paramValue);
                }
            }
        }
        setPagesAttribute(bookService.countAll(), request);
        return bookService.getAllPagination(pageNumber, ConfigParameter.ITEMS_ON_PAGE);
    }
}
