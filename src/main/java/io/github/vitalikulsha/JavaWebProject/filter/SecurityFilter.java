package io.github.vitalikulsha.JavaWebProject.filter;

import io.github.vitalikulsha.JavaWebProject.domain.Role;
import io.github.vitalikulsha.JavaWebProject.domain.User;
import io.github.vitalikulsha.JavaWebProject.util.SecurityUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        User loginUser = getLoginUser(req.getSession());
        if (servletPath.equals("/login")) {
            chain.doFilter(req, resp);
            return;
        }
        HttpServletRequest wrapRequest = req;
        if (loginUser != null) {
            String userName = loginUser.getLogin();
            Role userRole = loginUser.getRole();

        }
//        if (SecurityUtil.isSecurityPage(req)) {
//            if (loginUser == null) {
//                String requestUri = req.getRequestURI();
//                int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
//
//                response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
//                return;
//            }
//
//            // Проверить пользователь имеет действительную роль или нет?
//            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
//            if (!hasPermission) {
//
//                RequestDispatcher dispatcher //
//                        = request.getServletContext().getRequestDispatcher("/WEB-INF/views/accessDeniedView.jsp");
//
//                dispatcher.forward(request, response);
//                return;
//            }
//        }
    }

    @Override
    public void destroy() {
    }

    private User getLoginUser(HttpSession session) {
        return (User) session.getAttribute("loginUser");
    }
}
