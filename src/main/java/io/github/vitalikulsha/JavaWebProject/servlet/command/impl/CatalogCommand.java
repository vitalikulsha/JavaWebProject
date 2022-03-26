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
        Pagination<BookDto> pagination = new Pagination<>(ConfigParameter.ITEM_PER_PAGE);
        String url = getUrl(request);
        try {
            List<BookDto> catalog = getCatalog(request);
            log.info("url: " + url + ", catalog size: " + catalog.size());
            if (catalog.isEmpty()) {
                session.setAttribute(Attribute.BOOK_FOUND, false);
                return new CommandInfo(UserPath.BOOK_SEARCH.getPath(), RoutingType.REDIRECT);
            } else {
                request.setAttribute(Attribute.URL, url);
                session.setAttribute(Attribute.BOOK_FOUND, true);
                pagination.paginate(catalog, request, Attribute.CATALOG);
                return new CommandInfo(Page.CATALOG, RoutingType.FORWARD);
            }
        } catch (ServiceException e) {
            log.error("Unable to get book catalog: " + e.getMessage());
        }
        return new CommandInfo(Page.ERROR_500, RoutingType.FORWARD);
    }

    private String getUrl(HttpServletRequest request) {
        String url = request.getContextPath() + request.getServletPath() + "?";
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove(Parameter.PAGE);
        if (!params.isEmpty()) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                url = url + entry.getKey() + "=" + entry.getValue()[0];
            }
        }
        return url;
    }

    private List<BookDto> getCatalog(HttpServletRequest request) throws ServiceException {
        BookService bookService = ServiceFactory.instance().bookService();
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());
        params.remove(Parameter.PAGE);
        if(!params.isEmpty()){
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                String param = entry.getKey();
                log.info("param: " + param);
                switch (param) {
                    case (Parameter.BOOK_TITLE):
                        return removeQuantityBooksZero(bookService.getBooksByTitle(entry.getValue()[0]));
                    case (Parameter.AUTHOR_NAME):
                        return removeQuantityBooksZero(bookService.getBooksByAuthorName(entry.getValue()[0]));
                    case (Parameter.CATEGORY_NAME):
                        return removeQuantityBooksZero(bookService.getBooksByCategoryName(entry.getValue()[0]));
                }
            }
        }
        return removeQuantityBooksZero(bookService.getAll());
    }

    private List<BookDto> removeQuantityBooksZero(List<BookDto> books) {
        return books.stream()
                .filter(b -> b.getNumber() != 0)
                .collect(Collectors.toList());
    }
}
