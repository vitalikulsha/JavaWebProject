package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.dao.BookCatalogDao;
import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.domain.Book;
import io.github.vitalikulsha.JavaWebProject.domain.BookCatalog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        BookCatalogDao catalogDao = factory.bookCatalogDao();
        List<BookCatalog> catalogs = (List<BookCatalog>) session.getAttribute("bookCatalog");
        if (catalogs == null) {
            catalogs = catalogDao.getAll();
        }
        req.setAttribute("bookCatalog", catalogs);
        getServletContext().getRequestDispatcher("/WEB-INF/view/bookCatalog.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        BookDao bookDao = factory.bookDao();
        Book book = (Book) session.getAttribute("book");
        Integer bookId = Integer.valueOf(req.getParameter("bookId"));
        if(book==null){
            book = bookDao.getById(bookId).get();
        }
        req.setAttribute("book", book);
        resp.sendRedirect("/library/order");
    }
}
