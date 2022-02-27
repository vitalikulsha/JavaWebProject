package io.github.vitalikulsha.JavaWebProject.servlet.readerServlet;

import io.github.vitalikulsha.JavaWebProject.dto.BookDto;;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;
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
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        log.debug("LoginServlet starting");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.debug("LoginServlet doGet() starting");
        HttpSession session = req.getSession();
        BookService bookService = factory.bookService();
        BookDto bookDto = (BookDto) session.getAttribute(Attribute.BOOK);
        int bookId = Integer.parseInt(req.getParameter(Parameter.BOOK_ID));
        if (bookDto == null) {
            bookDto = bookService.getById(bookId);
        }
        req.setAttribute(Attribute.BOOK, bookDto);
        getServletContext().getRequestDispatcher("/WEB-INF/view/reader/order.jsp").forward(req, resp);
    }
}
