package io.github.vitalikulsha.javawebproject.servlet.filter;

import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.util.constant.JspValue;
import io.github.vitalikulsha.javawebproject.util.constant.SessionAttribute;
import io.github.vitalikulsha.javawebproject.servlet.path.AdminPath;
import io.github.vitalikulsha.javawebproject.servlet.path.GuestPath;
import io.github.vitalikulsha.javawebproject.servlet.path.UserPath;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Filter, that controls access of users with different roles to commands of the system.
 */
@Slf4j
@WebFilter("/*")
public class AccessFilter implements Filter {
    private final Map<Role, List<String>> rolePages = new EnumMap<>(Role.class);

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("AccessFilter initialization");
        List<String> guestPages = Arrays.stream(GuestPath.values())
                .map(GuestPath::getPath)
                .collect(Collectors.toList());
        rolePages.put(Role.GUEST, guestPages);

        List<String> userPages = Arrays.stream(UserPath.values())
                .map(UserPath::getPath)
                .collect(Collectors.toList());
        userPages.addAll(guestPages);
        rolePages.put(Role.READER, userPages);

        List<String> adminPages = Arrays.stream(AdminPath.values())
                .map(AdminPath::getPath)
                .collect(Collectors.toList());
        adminPages.addAll(guestPages);
        rolePages.put(Role.ADMIN, adminPages);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        log.debug("AccessFilter method doFilter() starting");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, getLocale(session));
        String servletPath = request.getServletPath();
        UserDTO user = (UserDTO) session.getAttribute(SessionAttribute.USER);
        Role role = (user == null) ? Role.GUEST : user.getRole();
        log.info("User role = " + role);
        if (servletPath == null) {
            log.error("Servlet path is empty");
            response.sendRedirect(request.getContextPath() + GuestPath.ERROR_404.getPath());
            return;
        }
        if (isRolePage(servletPath)) {
            if (!rolePages.get(role).contains(servletPath)) {
                log.error("No access rights to " + servletPath);
                response.sendRedirect(request.getContextPath() + GuestPath.ERROR_403.getPath());
                return;
            }
            log.info(servletPath + " available");
            chain.doFilter(servletRequest, servletResponse);
        } else {
            log.error("Not found: " + servletPath);
            response.sendRedirect(request.getContextPath() + GuestPath.ERROR_404.getPath());
        }
    }

    @Override
    public void destroy() {
        log.debug("AccessFilter destruction");
    }

    private String getLocale(HttpSession session) {
        String locale = (String) session.getAttribute(SessionAttribute.LOCALE);
        log.info("Locale: " + locale);
        return locale == null ? JspValue.EN : locale;
    }

    private boolean isRolePage(String servletPath) {
        return rolePages.get(Role.READER).contains(servletPath) || rolePages.get(Role.ADMIN).contains(servletPath);
    }
}