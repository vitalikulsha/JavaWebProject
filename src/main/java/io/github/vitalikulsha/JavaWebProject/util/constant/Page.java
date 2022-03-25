package io.github.vitalikulsha.JavaWebProject.util.constant;

public class Page {
    //general
    public final static String LOGIN = "/WEB-INF/view/login.jsp";
    public final static String REGISTER = "/WEB-INF/view/register.jsp";

    //error
    public final static String ERROR_403 = "/WEB-INF/view/error/403.jsp";
    public final static String ERROR_404 = "/WEB-INF/view/error/404.jsp";
    public final static String ERROR_500 = "/WEB-INF/view/error/500.jsp";

    //admin
    public final static String ADMIN = "/WEB-INF/view/admin/admin.jsp";
    public final static String BOOK_INFO = "/WEB-INF/view/admin/book-info.jsp";
    public final static String READER_INFO = "/WEB-INF/view/admin/reader-info.jsp";
    public final static String ORDER_INFO = "/WEB-INF/view/admin/order-info.jsp";
    public final static String ALL_ORDERS = "/WEB-INF/view/admin/all-orders.jsp";
    public final static String ALL_READERS = "/WEB-INF/view/admin/all-readers.jsp";
    public final static String ALL_BOOKS = "/WEB-INF/view/admin/all-books.jsp";

    //user
    public final static String READER = "/WEB-INF/view/reader/reader.jsp";
    public final static String READER_ORDERS = "/WEB-INF/view/reader/reader-orders.jsp";
    public final static String READER_ORDER_INFO = "/WEB-INF/view/reader/reader-order-info.jsp";
    public final static String BOOK_SEARCH = "/WEB-INF/view/reader/book-search.jsp";
    public final static String CATALOG = "/WEB-INF/view/reader/catalog.jsp";
    public final static String ORDER = "/WEB-INF/view/reader/order.jsp";
    public final static String EDIT = "/WEB-INF/view/reader/edit.jsp";

    private Page() {
    }
}
