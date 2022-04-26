package io.github.vitalikulsha.javawebproject.servlet;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.order.entity.OrderDTO;
import io.github.vitalikulsha.javawebproject.servlet.path.AdminPath;
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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void testAdminCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.ADMIN)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.ADMIN.getPath());
        when(request.getSession()).thenReturn(httpSession);
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/admin.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testAllBooksCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.ALL_BOOKS)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.ALL_BOOKS.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameter(RequestParameter.PAGE)).thenReturn("1");
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/all-books.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testAllOrdersCommandGetMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestDispatcher(Page.ALL_ORDERS)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.ALL_ORDERS.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameter(RequestParameter.PAGE)).thenReturn("1");
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/all-orders.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testAllOrdersCommandPostMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getServletPath()).thenReturn(AdminPath.ALL_ORDERS.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameter(RequestParameter.ORDER_ID)).thenReturn("3");
        when(request.getParameter(RequestParameter.ACTION)).thenReturn("cancel");
        servlet.doPost(request, response);
        verify(request, never()).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/admin/all-orders");
    }

    @Test
    public void testAllReadersCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.ALL_READERS)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.ALL_READERS.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameter(RequestParameter.PAGE)).thenReturn("1");
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/all-readers.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testBookInfoCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.BOOK_INFO)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.BOOK_INFO.getPath());
        when(request.getParameter(RequestParameter.BOOK_ID)).thenReturn("12300");
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/book-info.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testBookSearchCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.BOOK_SEARCH)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.BOOK_SEARCH.getPath());
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/reader/book-search.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testCatalogCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.CATALOG)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.CATALOG.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
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
    public void testEditReaderCommandGetMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestDispatcher(Page.EDIT)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.EDIT.getPath());
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request).getRequestDispatcher("/WEB-INF/view/reader/edit.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testEditReaderCommandPostMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getServletPath()).thenReturn(UserPath.EDIT.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.FIRST_NAME)).thenReturn("Vitali");
        when(request.getParameter(RequestParameter.LAST_NAME)).thenReturn("Kulsha");
        when(request.getParameter(RequestParameter.PHONE_NUMBER)).thenReturn(String.valueOf(375123456789L));
        when(request.getParameter(RequestParameter.EMAIL)).thenReturn("test@test.org");
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
        verify(response).sendRedirect("/library/reader");
    }

    @Test
    public void testEditReaderCommandPostMethodInvalidField() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestDispatcher(Page.EDIT)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.EDIT.getPath());
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.FIRST_NAME)).thenReturn("v");
        when(request.getParameter(RequestParameter.LAST_NAME)).thenReturn("k");
        when(request.getParameter(RequestParameter.PHONE_NUMBER)).thenReturn(String.valueOf(111123456789L));
        when(request.getParameter(RequestParameter.EMAIL)).thenReturn("test@test.org");
        DTOConverter<UserDTO, User> userConverter = DTOConverterFactory.instance().userDtoConverter();
        UserDTO userDTO = DataBase.USER_TABLE.stream()
                .filter(u -> u.getId() == 3)
                .findFirst()
                .map(userConverter::toDto)
                .get();
        when(httpSession.getAttribute(SessionAttribute.USER)).thenReturn(userDTO);
        servlet.doPost(request, response);
        verify(request).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/reader/edit.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testLocaleCommand() throws ServletException, IOException {
        when(request.getServletPath()).thenReturn(GuestPath.LOCALE.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.LOCALE)).thenReturn("en");
        when(httpSession.getAttribute(SessionAttribute.URL)).thenReturn("/reader");
        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/reader");
        verify(response, never()).sendRedirect("/library/login");
    }

    @Test
    public void testError403Command() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.ERROR_403)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(GuestPath.ERROR_403.getPath());
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/error/403.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testError404Command() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.ERROR_404)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(GuestPath.ERROR_404.getPath());
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/error/404.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testError500Command() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.ERROR_500)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(GuestPath.ERROR_500.getPath());
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/error/500.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testLoginCommandGetMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestDispatcher(Page.LOGIN)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(GuestPath.LOGIN.getPath());
        when(request.getSession()).thenReturn(httpSession);
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/login.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testLoginCommandPostMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getServletPath()).thenReturn(GuestPath.LOGIN.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.LOGIN)).thenReturn("User");
        when(request.getParameter(RequestParameter.PASSWORD)).thenReturn("user");
        servlet.doPost(request, response);
        verify(request, times(2)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/reader");
    }

    @Test
    public void testOrderCommandGetMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestDispatcher(Page.ORDER)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.ORDER.getPath());
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.BOOK_ID)).thenReturn("10100");
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request).getRequestDispatcher("/WEB-INF/view/reader/order.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testOrderCommandPostMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getServletPath()).thenReturn(UserPath.ORDER.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
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

    @Test
    public void testOrderInfoCommandGetMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestDispatcher(Page.ORDER_INFO)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.ORDER_INFO.getPath());
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.ORDER_ID)).thenReturn("3");
        servlet.doGet(request, response);
        verify(request, times(1)).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/order-info.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testOrderInfoCommandPostMethodActionCancel() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getServletPath()).thenReturn(AdminPath.ORDER_INFO.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.ACTION)).thenReturn("cancel");
        DTOConverter<OrderDTO, Order> orderConverter = DTOConverterFactory.instance().orderDtoConverter();
        OrderDTO orderDTO = DataBase.ORDER_TABLE.stream()
                .filter(o -> o.getId() == 2)
                .findFirst()
                .map(orderConverter::toDto)
                .get();
        when(httpSession.getAttribute(SessionAttribute.ORDER)).thenReturn(orderDTO);
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/admin/all-orders");
    }

    @Test
    public void testOrderInfoCommandPostMethodActionApprove() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestDispatcher(Page.ORDER_INFO)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.ORDER_INFO.getPath());
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.ACTION)).thenReturn("approve");
        DTOConverter<OrderDTO, Order> orderConverter = DTOConverterFactory.instance().orderDtoConverter();
        OrderDTO orderDTO = DataBase.ORDER_TABLE.stream()
                .filter(o -> o.getId() == 2)
                .findFirst()
                .map(orderConverter::toDto)
                .get();
        when(httpSession.getAttribute(SessionAttribute.ORDER)).thenReturn(orderDTO);
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/order-info.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testReaderCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.READER)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.READER.getPath());
        when(request.getSession()).thenReturn(httpSession);
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/reader/reader.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testReaderInfoCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.READER_INFO)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(AdminPath.READER_INFO.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getParameter(RequestParameter.READER_ID)).thenReturn("3");
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/admin/reader-info.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testReaderOrderInfoCommand() throws ServletException, IOException {
        when(request.getRequestDispatcher(Page.READER_ORDER_INFO)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.READER_ORDER_INFO.getPath());
        when(request.getParameter(RequestParameter.ORDER_ID)).thenReturn("3");
        servlet.doGet(request, response);
        verify(request, never()).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/reader/reader-order-info.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testReaderOrdersCommandGetMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestDispatcher(Page.READER_ORDERS)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(UserPath.READER_ORDERS.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
        DTOConverter<UserDTO, User> userConverter = DTOConverterFactory.instance().userDtoConverter();
        UserDTO userDTO = DataBase.USER_TABLE.stream()
                .filter(u -> u.getId() == 3)
                .findFirst()
                .map(userConverter::toDto)
                .get();
        when(httpSession.getAttribute(SessionAttribute.USER)).thenReturn(userDTO);
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request).getRequestDispatcher("/WEB-INF/view/reader/reader-orders.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testReaderOrdersCommandPostMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getServletPath()).thenReturn(UserPath.READER_ORDERS.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
        when(request.getParameter(RequestParameter.ORDER_ID)).thenReturn("1");
        when(request.getParameter(RequestParameter.ACTION)).thenReturn("refund");
        servlet.doPost(request, response);
        verify(request, times(1)).getSession();
        verify(response, times(1)).sendRedirect(any());
        verify(response).sendRedirect("/library/reader/reader-orders");
    }

    @Test
    public void testRegisterCommandGetMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestDispatcher(Page.REGISTER)).thenReturn(dispatcher);
        when(request.getServletPath()).thenReturn(GuestPath.REGISTER.getPath());
        when(request.getSession()).thenReturn(httpSession);
        servlet.doGet(request, response);
        verify(request).getSession();
        verify(request, times(1)).getRequestDispatcher("/WEB-INF/view/register.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testRegisterCommandPostMethod() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getServletPath()).thenReturn(GuestPath.REGISTER.getPath());
        when(request.getContextPath()).thenReturn("/library");
        when(request.getSession()).thenReturn(httpSession);
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

    @After
    public void finish() {
        session.finishMocking();
    }
}