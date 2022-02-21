package io.github.vitalikulsha.JavaWebProject.filter;

import io.github.vitalikulsha.JavaWebProject.domain.User;
import io.github.vitalikulsha.JavaWebProject.util.constant.SessionAttribute;
import io.github.vitalikulsha.JavaWebProject.util.path.AdminPages;
import io.github.vitalikulsha.JavaWebProject.util.path.UserPages;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebFilter("/*")
public class AccessFilter implements Filter {
    private final Map<User.Role, List<String>> rolePages = new EnumMap<>(User.Role.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<String> userPages = Arrays.stream(UserPages.values())
                .map(UserPages::getPath)
                .collect(Collectors.toList());
        rolePages.put(User.Role.USER, userPages);

        List<String> adminPath= Arrays.stream(AdminPages.values())
                .map(AdminPages::getPath)
                .collect(Collectors.toList());
        rolePages.put(User.Role.ADMIN, adminPath);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String servletPath = req.getServletPath();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (!isAdminPage(servletPath) && !isUserPage(servletPath)) {
            if (user == null) {
                chain.doFilter(request, response);
            } else if (user.getRole() == User.Role.USER) {
                res.sendRedirect("/library/reader/book-catalog");
            } else if (user.getRole() == User.Role.ADMIN) {
                res.sendRedirect("/library/login.jsp");
            } else {
                res.sendRedirect("/library/login.jsp");
            }
            return;
        } else if (user != null && isAuthorized(servletPath, user)) {
            chain.doFilter(request, response);
        }
        res.sendRedirect("/WEB-INF/view/login.jsp");
    }

    @Override
    public void destroy() {
    }

    private boolean isAdminPage(String servletPath) {
        return rolePages.get(User.Role.ADMIN).contains(servletPath);
    }

    private boolean isUserPage(String servletPath) {
        return rolePages.get(User.Role.USER).contains(servletPath);
    }

    private boolean isAuthorized(String servletPath, User user) {
        return (isAdminPage(servletPath) && user.getRole() == User.Role.ADMIN)
                || (isUserPage(servletPath) && user.getRole() == User.Role.USER);
    }


}
