-- DDL

CREATE TABLE CATEGORY
(
    ID       INTEGER PRIMARY KEY,
    NAME     VARCHAR(50)
);

CREATE TABLE BOOK
(
    BOOK_ID      INTEGER PRIMARY KEY,
    TITLE        VARCHAR(100),
    YEARISSUE    INTEGER,
    NUMBERPAGES  INTEGER,
    CATEGORY     INTEGER,
    CONSTRAINT FK_Category FOREIGN KEY (CATEGORY) REFERENCES CATEGORY (ID)
);

CREATE TABLE AUTHOR
(
    AUTHOR_ID  INTEGER PRIMARY KEY,
    FIRSTNAME  VARCHAR(20),
    LASTNAME   VARCHAR(20)
);

CREATE TABLE BOOK_AUTHOR
(
    BOOK_ID    INTEGER,
    AUTHOR_ID  INTEGER,
    PRIMARY KEY (BOOK_ID, AUTHOR_ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR
);

CREATE TABLE BOOK_CATALOG
(
    BOOK_ID    INTEGER,
    NUMBER     INTEGER,
    PRIMARY KEY (BOOK_ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK
);

CREATE TABLE REGISTRATION
(
    REG_ID     INTEGER PRIMARY KEY,
    BOOK_ID    INTEGER,
    USER_ID    INTEGER,
    LOCATION   VARCHAR(20),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK,
    FOREIGN KEY (USER_ID) REFERENCES USER
);

CREATE TABLE USER
(
    USER_ID    INTEGER PRIMARY KEY,
    LOGIN      VARCHAR(20),
    PASSWORD   VARCHAR(20),
    ROLE       VARCHAR(10)
);

COMMIT;
