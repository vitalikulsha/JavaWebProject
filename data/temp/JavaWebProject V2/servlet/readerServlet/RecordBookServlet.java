package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
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
public class RecordBookServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = DaoFactory.instance();
        log.debug("BookCatalogServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("BookCatalogServlet doGet() starting");
        HttpSession session = req.getSession();

        BookDao bookDao = factory.bookDao();
        @SuppressWarnings("unchecked")
        List<Book> catalog = (List<Book>) session.getAttribute("catalog");
        if (catalog == null) {
            catalog = bookDao.getAll();
        }
        req.setAttribute("catalog", catalog);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        log.debug("BookCatalogServlet doPost() starting");

        BookDao bookDao = factory.bookDao();
        @SuppressWarnings("unchecked")
        List<Book> catalog = (List<Book>) session.getAttribute("catalog");
        String bookTitle = req.getParameter("bookTitle");
        String authorName = req.getParameter("authorName");
        String categoryName = req.getParameter("categoryName");
        if (bookTitle != null) {
            catalog = bookDao.getByBookTitle(bookTitle);
        } else if (authorName != null) {
            catalog = bookDao.getByAuthorName(authorName);
        } else if (categoryName != null) {
            catalog = bookDao.getByCategoryName(categoryName);
        }
        req.setAttribute("catalog", catalog);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/catalog.jsp").forward(req, resp);
    }
}
