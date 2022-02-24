package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dao.RecordBookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.entity.RecordBook;
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
@WebServlet("/reader/record-book")
public class RecordBookServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = DaoFactory.getInstance();
        log.debug("BookCatalogServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("BookCatalogServlet doGet() starting");
        HttpSession session = req.getSession();
        RecordBookDao catalogDao = factory.getBookCatalogDao();
        @SuppressWarnings("unchecked")
        List<RecordBook> catalogs = (List<RecordBook>) session.getAttribute("recordBook");
        if (catalogs == null) {
            catalogs = catalogDao.getAll();
        }
        req.setAttribute("recordBook", catalogs);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/record-book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        log.debug("BookCatalogServlet doPost() starting");
        RecordBookDao catalogDao = factory.getBookCatalogDao();
        @SuppressWarnings("unchecked")
        List<RecordBook> records = (List<RecordBook>) session.getAttribute("recordBook");
        String bookTitle = req.getParameter("bookTitle");
        String authorName = req.getParameter("authorName");
        String categoryName = req.getParameter("categoryName");
        if (bookTitle != null) {
            records = catalogDao.getByName(bookTitle);
        } else if (authorName != null) {
            records = catalogDao.getByAuthorName(authorName);
        } else if (categoryName != null) {
            records = catalogDao.getByCategoryName(categoryName);
        }
        req.setAttribute("recordBook", records);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/record-book.jsp").forward(req, resp);
    }
}
