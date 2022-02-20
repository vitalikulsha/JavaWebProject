package io.github.vitalikulsha.JavaWebProject.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

public class UrlPatternUtil {

    private static boolean hasUrPattern(ServletContext servletContext, String urlPattern) {
        Map<String, ? extends ServletRegistration> servletMap = servletContext.getServletRegistrations();
        for (String servletName : servletMap.keySet()) {
            ServletRegistration sr = servletMap.get(servletName);
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static String getUrlPattern(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = null;
        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
            return urlPattern;
        }
        urlPattern = servletPath;
        if (hasUrPattern(servletContext, urlPattern)) {
            return urlPattern;
        }
        int i = servletPath.lastIndexOf('.');
        if (i != -1) {
            String ext = servletPath.substring(i + 1);
            urlPattern = "*." + ext;
            if (hasUrPattern(servletContext, urlPattern)) {
                return urlPattern;
            }
        }
        return "/";
    }
}
