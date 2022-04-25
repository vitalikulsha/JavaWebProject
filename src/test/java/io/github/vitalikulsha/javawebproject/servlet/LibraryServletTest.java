package io.github.vitalikulsha.javawebproject.servlet;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.servlet.path.GuestPath;
import io.github.vitalikulsha.javawebproject.servlet.path.UserPath;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class LibraryServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession httpSession;

    LibraryServlet servlet;
    MockitoSession session;

    @Before
    public void init() throws ServletException {
        session = mockitoSession().initMocks(this).startMocking();
        servlet = new LibraryServlet();
        servlet.init();
    }

    @Test
    public void testLoginCommandGetMethod() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(GuestPath.LOGIN.getPath());
        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getRequestDispatcher(Page.LOGIN)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/login.jsp");
        verify(dispatcher).forward(request, response);
        verify(request).getSession();
    }

    @Test
    public void testLoginCommandPostMethod() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(GuestPath.LOGIN.getPath());
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.LOGIN)).thenReturn("User");
        when(request.getParameter(RequestParameter.PASSWORD)).thenReturn("user");
        when(request.getContextPath()).thenReturn("/library");
        servlet.doPost(request, response);
        verify(request, times(2)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/reader");
    }

    @Test
    public void testRegisterCommandGetMethod() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(GuestPath.REGISTER.getPath());
        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getRequestDispatcher(Page.REGISTER)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/register.jsp");
        verify(dispatcher).forward(request, response);
        verify(request).getSession();
    }

    @Test
    public void testRegisterCommandPostMethod() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(GuestPath.REGISTER.getPath());
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameter(RequestParameter.LOGIN)).thenReturn("Vitali");
        when(request.getParameter(RequestParameter.PASSWORD)).thenReturn("VVvv1234");
        when(request.getParameter(RequestParameter.FIRST_NAME)).thenReturn("Виталий");
        when(request.getParameter(RequestParameter.LAST_NAME)).thenReturn("Кульша");
        when(request.getParameter(RequestParameter.PHONE_NUMBER)).thenReturn(String.valueOf(375291234567L));
        when(request.getParameter(RequestParameter.EMAIL)).thenReturn("vvv@vvv.vv");
        servlet.doPost(request, response);
        verify(request, times(2)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/reader");
    }

    @Test
    public void testLocaleCommand() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(GuestPath.LOCALE.getPath());
        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameter(RequestParameter.LOCALE)).thenReturn("en");
        when(httpSession.getAttribute(SessionAttribute.URL)).thenReturn("/reader");
        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/reader");
        verify(response, never()).sendRedirect("/library/login");
    }

    @Test
    public void testReaderCommand() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(UserPath.READER.getPath());
        when(request.getSession()).thenReturn(httpSession);
        when(request.getRequestDispatcher(Page.READER)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/reader/reader.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testBookSearchCommand() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(UserPath.BOOK_SEARCH.getPath());
        when(request.getRequestDispatcher(Page.BOOK_SEARCH)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/reader/book-search.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testCatalogCommand() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(UserPath.CATALOG.getPath());
        when(request.getRequestDispatcher(Page.CATALOG)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameterMap()).thenReturn(new HashMap<>() {{
            this.put("page", new String[]{"1"});
            this.put("bookTitle", new String[]{"и"});
        }});
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request).getRequestDispatcher("/WEB-INF/view/reader/catalog.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testOrderCommandGetMethod() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(UserPath.ORDER.getPath());
        when(request.getRequestDispatcher(Page.ORDER)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(httpSession);
        when(request.getMethod()).thenReturn("GET");
        when(request.getParameter(RequestParameter.BOOK_ID)).thenReturn("10100");
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request).getRequestDispatcher("/WEB-INF/view/reader/order.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testOrderCommandPostMethod() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(UserPath.ORDER.getPath());
        when(request.getSession()).thenReturn(httpSession);
        when(request.getContextPath()).thenReturn("/library");
        when(request.getMethod()).thenReturn("POST");
        when(request.getParameter(RequestParameter.RESERVE_STATUS)).thenReturn("LOANED");
        DTOConverter<BookDTO, Book> bookConverter = DTOConverterFactory.instance().bookDtoConverter();
        BookDTO bookDTO = DataBase.BOOK_TABLE.stream()
                .filter(b -> b.getId() == 12300)
                .findFirst()
                .map(bookConverter::toDto)
                .get();
        when(httpSession.getAttribute(SessionAttribute.BOOK)).thenReturn(bookDTO);
        DTOConverter<UserDTO, User> userConverter = DTOConverterFactory.instance().userDtoConverter();
        UserDTO userDTO = DataBase.USER_TABLE.stream()
                .filter(u -> u.getId() == 3)
                .findFirst()
                .map(userConverter::toDto)
                .get();
        when(httpSession.getAttribute(SessionAttribute.USER)).thenReturn(userDTO);
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/reader/reader-orders");
    }

    @After
    public void finish() {
        session.finishMocking();
    }
}