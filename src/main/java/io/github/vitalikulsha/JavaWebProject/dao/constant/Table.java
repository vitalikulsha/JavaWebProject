package io.github.vitalikulsha.JavaWebProject.dao.constant;

public enum Table {
    BOOK("book"),
    AUTHOR("author"),
    CATEGORY("category"),
    BOOK_AUTHOR("book_author"),
    RECORD_BOOK("record_book"),
    USER("user"),
    ORDER_BOOK("order_book");

    private String tableName;

    Table(String tableName) {
        this.tableName = tableName;
    }

    public String get() {
        return tableName;
    }
}
