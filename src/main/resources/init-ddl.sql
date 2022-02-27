-- DDL

CREATE TABLE CATEGORY
(
    CATEGORY_ID  INT NOT NULL PRIMARY KEY,
    NAME         VARCHAR(50) NOT NULL
);

CREATE TABLE BOOK
(
    BOOK_ID           INT NOT NULL PRIMARY KEY,
    TITLE             VARCHAR(100) NOT NULL,
    PUBLICATIONYEAR   INT,
    NUMBERPAGES       INT,
    CATEGORY          INT NOT NULL,
    NUMBER            INT,
    CONSTRAINT FK_Category FOREIGN KEY (CATEGORY) REFERENCES CATEGORY (CATEGORY_ID)
);

CREATE TABLE AUTHOR
(
    AUTHOR_ID  INT NOT NULL PRIMARY KEY,
    FIRSTNAME  VARCHAR(20),
    LASTNAME   VARCHAR(20)
);

CREATE TABLE BOOK_AUTHOR
(
    BOOK_ID    INT NOT NULL,
    AUTHOR_ID  INT NOT NULL,
    PRIMARY KEY (BOOK_ID, AUTHOR_ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR
);

CREATE TABLE RECORD_BOOK
(
    BOOK_ID  INT NOT NULL,
    NUMBER   INT,
    PRIMARY KEY (BOOK_ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK
);

CREATE TABLE USER
(
    USER_ID      INT NOT NULL PRIMARY KEY,
    LOGIN        VARCHAR(20) NOT NULL,
    PASSWORD     VARCHAR(20) NOT NULL,
    USERNAME     VARCHAR(20) NOT NULL,
    PHONENUMBER  BIGINT,
    EMAIL        VARCHAR(20) NOT NULL,
    ROLE         VARCHAR(10) NOT NULL
);

CREATE TABLE ORDER_BOOK
(
    ORDER_ID  INT NOT NULL PRIMARY KEY,
    BOOK_ID   INT NOT NULL,
    USER_ID   INT NOT NULL,
    RESERVED  VARCHAR(20) NOT NULL,
    APPROVED  BOOLEAN NOT NULL,
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK,
    FOREIGN KEY (USER_ID) REFERENCES USER
);

COMMIT;
