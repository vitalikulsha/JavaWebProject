package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.dao.BookCatalogDao;
import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.domain.Book;
import io.github.vitalikulsha.JavaWebProject.domain.BookCatalog;
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
@WebServlet("/reader/book-catalog")
public class BookCatalogServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = new DaoFactory();
        log.debug("BookCatalogServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("BookCatalogServlet doGet() starting");
        HttpSession session = req.getSession();
        BookCatalogDao catalogDao = factory.bookCatalogDao();
        @SuppressWarnings("unchecked")
        List<BookCatalog> catalogs = (List<BookCatalog>) session.getAttribute("bookCatalog");
        if (catalogs == null) {
            catalogs = catalogDao.getAll();
        }
        req.setAttribute("bookCatalog", catalogs);
        getServletContext().getRequestDispatcher("/WEB-INF/view/book-catalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        log.debug("BookCatalogServlet doPost() starting");
        BookCatalogDao catalogDao = factory.bookCatalogDao();
        @SuppressWarnings("unchecked")
        List<BookCatalog> catalogs = (List<BookCatalog>) session.getAttribute("bookCatalog");
        String bookTitle = req.getParameter("bookTitle");
        String authorName = req.getParameter("authorName");
        String categoryName = req.getParameter("categoryName");
        if (bookTitle != null) {
            catalogs = catalogDao.getByName(bookTitle);
        } else if(authorName !=null){
            catalogs = catalogDao.getByAuthorName(authorName);
        } else if(categoryName !=null){
            catalogs = catalogDao.getByCategoryName(categoryName);
        }
        req.setAttribute("bookCatalog", catalogs);
        getServletContext().getRequestDispatcher("/WEB-INF/view/book-catalog.jsp").forward(req, resp);
    }
}
