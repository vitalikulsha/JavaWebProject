package io.github.vitalikulsha.javawebproject.util.tags;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom tag: displays an error message
 */
@Slf4j
public class ErrorMessageTag extends TagSupport {
    private int error;
    private String locale;

    public void setError(int error) {
        this.error = error;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        Locale loc = new Locale(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("locale", loc);
        JspWriter out = pageContext.getOut();
        try {
            switch (error) {
                case 403:
                    out.write(String.format("<h3>%s</h3>", bundle.getString("403.error")));
                    out.write(String.format("<h4>%s</h4>", bundle.getString("403.message")));
                    break;
                case 404:
                    out.write(String.format("<h3>%s</h3>", bundle.getString("404.error")));
                    out.write(String.format("<h4>%s</h4>", bundle.getString("404.message")));
                    break;
                case 500:
                    out.write(String.format("<h3>%s</h3>", bundle.getString("500.error")));
                    out.write(String.format("<h4>%s</h4>", bundle.getString("500.message")));
            }
            log.info("Error code: " + error);
        } catch (IOException e) {
            String errorMsg = "Exception while processing tag \"err-msg\"";
            log.error(errorMsg, e);
            throw new JspException(errorMsg, e);
        }
        return SKIP_BODY;
    }
}
