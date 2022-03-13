package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.Pagination;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
import io.github.vitalikulsha.JavaWebProject.util.page.UserPages;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/reader/catalog")
public class CatalogServlet extends HttpServlet {
    private final static int ITEM_PER_PAGE = 5;
    private Pagination<BookDto> pagination;
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        pagination = new Pagination<>(ITEM_PER_PAGE);
        log.debug("BookCatalogServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("BookCatalogServlet doGet() starting");
        HttpSession session = request.getSession();
        BookService bookService = factory.bookService();
        List<BookDto> catalog;
        String bookTitle = request.getParameter(Parameter.BOOK_TITLE);
        String authorName = request.getParameter(Parameter.AUTHOR_NAME);
        String categoryName = request.getParameter(Parameter.CATEGORY_NAME);
        String page = request.getParameter(Parameter.PAGE);
        String url = session.getServletContext().getContextPath() + UserPages.CATALOG.getPage() + "?";
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
        session.setAttribute(Attribute.URL, url);
        request.setAttribute(Attribute.PAGES, pages);
        request.setAttribute(Attribute.CATALOG, catalog);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/catalog.jsp").forward(request, response);
    }
}
