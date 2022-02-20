package io.github.vitalikulsha.JavaWebProject.util;

import io.github.vitalikulsha.JavaWebProject.config.SecurityConfig;
import io.github.vitalikulsha.JavaWebProject.domain.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtil {

    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtil.getUrlPattern(request);
        Set<Role> roles = SecurityConfig.getAllRoles();
        for (Role role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtil.getUrlPattern(request);
        Set<Role> allRoles = SecurityConfig.getAllRoles();
        for (Role role : allRoles) {
            if (!request.isUserInRole(role.name())) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
