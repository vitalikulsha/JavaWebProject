-- DDL

CREATE TABLE CATEGORY
(
    ID       INTEGER NOT NULL PRIMARY KEY,
    NAME     VARCHAR(50) NOT NULL
);

CREATE TABLE BOOK
(
    ID           INTEGER NOT NULL PRIMARY KEY,
    NAME         VARCHAR(60) NOT NULL,
    PUBLISHER    VARCHAR(20),
    YEARISSUE    INTEGER,
    NUMBERPAGES  INTEGER,
    CATEGORY     INTEGER NOT NULL FOREIGN KEY (CATEGORY) REFERENCES CATEGORY (ID) 
);

CREATE TABLE AUTHOR
(
    ID         INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    FIRSTNAME  VARCHAR(20) NOT NULL,
    LASTNAME   VARCHAR(20) NOT NULL
);

CREATE TABLE BOOK_AUTHOR
(
    BOOK_ID    INTEGER NOT NULL,
    AUTHOR_ID  INTEGER NOT NULL,
    PRIMARY KEY (BOOK_ID, AUTHOR_ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR
);

COMMIT;
