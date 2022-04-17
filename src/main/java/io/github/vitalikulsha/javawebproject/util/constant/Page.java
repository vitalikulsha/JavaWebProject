package io.github.vitalikulsha.javawebproject.util.constant;

/**
 * List of paths to JSP pages.
 */
public class Page {
    //general
    public static final String LOGIN = "/WEB-INF/view/login.jsp";
    public static final String REGISTER = "/WEB-INF/view/register.jsp";

    //error
    public static final String ERROR_403 = "/WEB-INF/view/error/403.jsp";
    public static final String ERROR_404 = "/WEB-INF/view/error/404.jsp";
    public static final String ERROR_500 = "/WEB-INF/view/error/500.jsp";

    //admin
    public static final String ADMIN = "/WEB-INF/view/admin/admin.jsp";
    public static final String BOOK_INFO = "/WEB-INF/view/admin/book-info.jsp";
    public static final String READER_INFO = "/WEB-INF/view/admin/reader-info.jsp";
    public static final String ORDER_INFO = "/WEB-INF/view/admin/order-info.jsp";
    public static final String ALL_ORDERS = "/WEB-INF/view/admin/all-orders.jsp";
    public static final String ALL_READERS = "/WEB-INF/view/admin/all-readers.jsp";
    public static final String ALL_BOOKS = "/WEB-INF/view/admin/all-books.jsp";

    //user
    public static final String READER = "/WEB-INF/view/reader/reader.jsp";
    public static final String READER_ORDERS = "/WEB-INF/view/reader/reader-orders.jsp";
    public static final String READER_ORDER_INFO = "/WEB-INF/view/reader/reader-order-info.jsp";
    public static final String BOOK_SEARCH = "/WEB-INF/view/reader/book-search.jsp";
    public static final String CATALOG = "/WEB-INF/view/reader/catalog.jsp";
    public static final String ORDER = "/WEB-INF/view/reader/order.jsp";
    public static final String EDIT = "/WEB-INF/view/reader/edit.jsp";

    private Page() {
    }
}
