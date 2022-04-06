package io.github.vitalikulsha.javawebproject.servlet.command.impl;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.book.service.BookService;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import io.github.vitalikulsha.javawebproject.servlet.command.Command;
import io.github.vitalikulsha.javawebproject.servlet.command.CommandInfo;
import io.github.vitalikulsha.javawebproject.servlet.command.RoutingType;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.path.UserPath;
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
        Pagination<BookDTO> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        session.removeAttribute(SessionAttribute.BOOK_EXISTS);
        String url = getUrl(request);
        try {
            List<BookDTO> catalog = getCatalog(request);
            log.info("url: " + url + ", catalog size: " + catalog.size());
            if (catalog.isEmpty()) {
                session.setAttribute(SessionAttribute.BOOK_FOUND, false);
                return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
            } else {
                request.setAttribute(SessionAttribute.URL, url);
                session.setAttribute(SessionAttribute.BOOK_FOUND, true);
                pagination.paginate(catalog, request, SessionAttribute.CATALOG);
                return new CommandInfo(Page.CATALOG, RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            log.error("Unable to get book catalog: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }

    private String getUrl(HttpServletRequest request) {
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
        if (!params.isEmpty()) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                String param = entry.getKey();
                log.info("param: " + param);
                switch (param) {
                    case (RequestParameter.BOOK_TITLE):
                        return removeQuantityBooksZero(bookService.getBooksByTitle(entry.getValue()[0]));
                    case (RequestParameter.AUTHOR_NAME):
                        return removeQuantityBooksZero(bookService.getBooksByAuthorName(entry.getValue()[0]));
                    case (RequestParameter.CATEGORY_NAME):
                        return removeQuantityBooksZero(bookService.getBooksByCategoryName(entry.getValue()[0]));
                }
            }
        }
        return removeQuantityBooksZero(bookService.getAll());
    }

    private List<BookDTO> removeQuantityBooksZero(List<BookDTO> books) {
        return books.stream()
                .filter(b -> b.getQuantity() != 0)
                .collect(Collectors.toList());
    }
}