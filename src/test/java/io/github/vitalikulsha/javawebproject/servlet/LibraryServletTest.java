package io.github.vitalikulsha.javawebproject.servlet;

import io.github.vitalikulsha.javawebproject.servlet.path.GuestPath;
import io.github.vitalikulsha.javawebproject.util.constant.Page;
import io.github.vitalikulsha.javawebproject.util.constant.RequestParameter;
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
    public void testLoginCommandWithGetMethod() throws ServletException, IOException {
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
    public void testLoginCommandWithPostMethod() throws ServletException, IOException {
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

    @After
    public void finish() {
        session.finishMocking();
    }
}