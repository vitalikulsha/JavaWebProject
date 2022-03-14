package io.github.vitalikulsha.JavaWebProject.filter;

import io.github.vitalikulsha.JavaWebProject.dto.UserDto;
import io.github.vitalikulsha.JavaWebProject.entity.Role;
import io.github.vitalikulsha.JavaWebProject.entity.User;
import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.page.AdminPages;
import io.github.vitalikulsha.JavaWebProject.util.page.GuestPages;
import io.github.vitalikulsha.JavaWebProject.util.page.UserPages;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@WebFilter("/*")
public class AccessFilter implements Filter {
    private final Map<Role, List<String>> rolePages = new EnumMap<>(Role.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("AccessFilter initialization");
        List<String> userPages = Arrays.stream(UserPages.values())
                .map(UserPages::getPage)
                .collect(Collectors.toList());
        rolePages.put(Role.USER, userPages);

        List<String> adminPath = Arrays.stream(AdminPages.values())
                .map(AdminPages::getPage)
                .collect(Collectors.toList());
        rolePages.put(Role.ADMIN, adminPath);

        List<String> guestPath = Arrays.stream(GuestPages.values())
                .map(GuestPages::getPage)
                .collect(Collectors.toList());
        rolePages.put(Role.GUEST, guestPath);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        log.debug("AccessFilter method doFilter() starting");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String servletPath = request.getServletPath();
        log.debug("Servlet path = " + servletPath);
        UserDto user = (UserDto) session.getAttribute(Attribute.USER);
        if (servletPath.equals(UserPages.LOGOUT.getPage())) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        log.info("Session attribute user = " + session.getAttribute(Attribute.USER));
        log.debug("isAdminPage = " + isAdminPage(servletPath) + "isUserPage = " + isUserPage(servletPath));
        if (isAdminPage(servletPath) && isUserPage(servletPath) && isGuestPage(servletPath)) {
            log.debug("Is method isAdminPage && isUserPage");
            if (user == null) {
                log.debug("Is user == null");
                chain.doFilter(servletRequest, servletResponse);
                log.debug("The AccessFilter has worked");
            } else if (user.getRole() == Role.USER) {
                response.sendRedirect("/library/reader");
            } else if (user.getRole() == Role.ADMIN) {
                response.sendRedirect("/library/login");
            } else {
                response.sendRedirect("/library/login");
            }
            return;
        } else if (user != null && isAuthorized(servletPath, user)) {
            log.debug("User not null and isAuthorized");
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        log.debug("No rights: redirecting login");
        response.sendRedirect("/library/login");
    }

    @Override
    public void destroy() {
        log.debug("AccessFilter destruction");
    }

    private boolean isAdminPage(String servletPath) {
        return rolePages.get(Role.ADMIN).contains(servletPath);
    }

    private boolean isUserPage(String servletPath) {
        return rolePages.get(Role.USER).contains(servletPath);
    }

    private boolean isGuestPage(String servletPath) {
        return rolePages.get(Role.GUEST).contains(servletPath);
    }

    private boolean isAuthorized(String servletPath, UserDto user) {
        return (isAdminPage(servletPath) && user.getRole() == Role.ADMIN)
                || (isUserPage(servletPath) && user.getRole() == Role.USER);
    }


}
