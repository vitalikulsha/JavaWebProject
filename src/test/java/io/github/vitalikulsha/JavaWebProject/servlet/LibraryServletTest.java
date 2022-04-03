package io.github.vitalikulsha.JavaWebProject.servlet;

import io.github.vitalikulsha.JavaWebProject.util.constant.Page;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class LibraryServletTest {

    @Test
    public void doGetTest() throws ServletException, IOException {
        final LibraryServlet servlet = new LibraryServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(Page.LOGIN)).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher(Page.LOGIN);
        verify(request).getSession();
        verify(dispatcher).forward(request, response);
    }
}