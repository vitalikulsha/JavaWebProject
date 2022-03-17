package io.github.vitalikulsha.JavaWebProject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) {
        try {
            response.getWriter().print("Hello, world!!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
