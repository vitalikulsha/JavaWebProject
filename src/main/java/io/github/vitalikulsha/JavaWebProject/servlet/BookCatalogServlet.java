package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.dao.BookCatalogDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.domain.BookCatalog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/book-catalog")
public class BookCatalogServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = new DaoFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookCatalogDao catalogDao = factory.bookCatalogDao();
        List<BookCatalog> catalogs = catalogDao.getAll();
        req.setAttribute("bookCatalog", catalogs);
        getServletContext().getRequestDispatcher("/WEB-INF/view/bookCatalog.jsp").forward(req, resp);
    }
}
