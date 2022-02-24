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

@Slf4j
@WebServlet("/reader/order")
public class OrderServlet extends HttpServlet {
    private DaoFactory factory;

    @Override
    public void init() throws ServletException {
        factory = DaoFactory.getInstance();
        log.debug("LoginServlet starting");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("LoginServlet doGet() starting");
        HttpSession session = req.getSession();
        BookDao bookDao = factory.getBookDao();
        Book book = (Book) session.getAttribute("book");
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        if (book == null) {
            book = bookDao.getById(bookId);
        }
        req.setAttribute("book", book);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/order.jsp").forward(req, resp);
    }
}
