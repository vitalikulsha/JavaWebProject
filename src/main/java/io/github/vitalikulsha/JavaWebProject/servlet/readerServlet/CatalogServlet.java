package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
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
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        log.debug("BookCatalogServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("BookCatalogServlet doGet() starting");
        HttpSession session = req.getSession();
        BookService bookService = factory.bookService();
        @SuppressWarnings("unchecked")
        List<BookDto> catalog = (List<BookDto>) session.getAttribute(Attribute.CATALOG);
        if (catalog == null) {
            catalog = bookService.getAll();
        }
        req.setAttribute(Attribute.CATALOG, catalog);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        log.debug("BookCatalogServlet doPost() starting");
        BookService bookService = factory.bookService();
        @SuppressWarnings("unchecked")
        List<BookDto> catalog = (List<BookDto>) session.getAttribute(Attribute.CATALOG);
        String bookTitle = req.getParameter(Attribute.BOOK_TITLE);
        String authorName = req.getParameter(Attribute.AUTHOR_NAME);
        String categoryName = req.getParameter(Attribute.CATEGORY_NAME);
        if (bookTitle != null) {
            catalog = bookService.getBooksByTitle(bookTitle);
        } else if (authorName != null) {
            catalog = bookService.getBooksByAuthorName(authorName);
        } else if (categoryName != null) {
            catalog = bookService.getBooksByCategoryName(categoryName);
        }
        req.setAttribute(Attribute.CATALOG, catalog);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/catalog.jsp").forward(req, resp);
    }
}
