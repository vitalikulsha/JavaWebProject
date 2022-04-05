package io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant;

/**
 * Table column names from the database
 */
public enum Column {
    //book table
    BOOK_ID,
    TITLE,
    PUBLICATIONYEAR,
    NUMBERPAGES,
    QUANTITY,
    CATEGORY,

    //category table
    CATEGORY_ID,
    NAME,

    //author table
    AUTHOR_ID,
    FIRSTNAME,
    LASTNAME,

    //user table
    USER_ID,
    LOGIN,
    PASSWORD,
    PHONENUMBER,
    EMAIL,
    ROLE,

    //order_book table
    ORDER_ID,
    RESERVED,
    APPROVED
}
