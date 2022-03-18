package io.github.vitalikulsha.JavaWebProject.util.constant;

public class Page {
    //admin
    public final static String ADMIN = "/WEB-INF/view/admin/admin.jsp";
    public final static String BOOK_INFO = "/WEB-INF/view/admin/book-info.jsp";
    public final static String READER_INFO = "/WEB-INF/view/admin/reader-info.jsp";
    public final static String ORDER_INFO = "/WEB-INF/view/admin/order-info.jsp";
    public final static String ALL_ORDERS = "/WEB-INF/view/admin/all-orders.jsp";
    public final static String ALL_READERS = "/WEB-INF/view/admin/all-readers.jsp";

    //user
    public final static String READER = "/WEB-INF/view/reader/reader.jsp";
    public final static String READER_ORDERS = "/WEB-INF/view/reader/reader-orders.jsp";
    public final static String BOOK_SEARCH = "/WEB-INF/view/reader/book-search.jsp";

    private Page() {
    }
}
