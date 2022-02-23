package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/reader/book-search")
public class BookSearchServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        log.debug("BookSearchServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("BookSearchServlet doGet() starting");
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/book-search.jsp").forward(req, resp);
    }
}
