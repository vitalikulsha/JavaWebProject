package io.github.vitalikulsha.javawebproject.servlet.filter;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter, that sets request and response encoding
 */
@Slf4j
@WebFilter("/*")
public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("EncodingFilter initialization");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(ConfigParameter.ENCODING);
        response.setContentType(ConfigParameter.CONTENT_TYPE);
        chain.doFilter(request, response);
        log.debug("The EncodingFilter has worked");
    }

    @Override
    public void destroy() {
        log.debug("EncodingFilter destruction");
    }
}
