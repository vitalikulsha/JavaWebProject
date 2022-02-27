package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/reader")
public class ReaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("BookSearchServlet doGet() starting");
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/reader.jsp").forward(req, resp);
    }
}
