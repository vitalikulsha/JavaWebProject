package io.github.vitalikulsha.JavaWebProject.servlet.adminServlet;

import io.github.vitalikulsha.JavaWebProject.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import io.github.vitalikulsha.JavaWebProject.service.ServiceFactory;
import io.github.vitalikulsha.JavaWebProject.service.UserService;
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
@WebServlet(urlPatterns = {"/admin", "/admin/book-info", "/admin/reader-info"})
public class AdminServlet extends HttpServlet {
    private ServiceFactory factory;

    @Override
    public void init() throws ServletException {
        factory = ServiceFactory.instance();
        log.debug("AdminServlet starting");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("AdminServlet doGet() starting");
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        BookService bookService = factory.bookService();
        UserService userService = factory.userService();
        BookDto bookDto = (BookDto) session.getAttribute(Attribute.BOOK);
        User reader = (User) session.getAttribute(Attribute.READER);
        String bookId = request.getParameter(Parameter.BOOK_ID);
        String readerId = request.getParameter(Parameter.READER_ID);
        reader = (readerId == null) ? reader : userService.getById(Integer.parseInt(readerId));
        bookDto = (bookId == null) ? bookDto : bookService.getById(Integer.parseInt(bookId));
        if (reader != null) {
            request.setAttribute(Attribute.READER, reader);
            getServletContext().getRequestDispatcher("/WEB-INF/view/admin/reader-info.jsp").forward(request, response);
        } else if (bookDto != null) {
            request.setAttribute(Attribute.BOOK, bookDto);
            getServletContext().getRequestDispatcher("/WEB-INF/view/admin/book-info.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/admin/admin.jsp").forward(request, response);
    }
}
