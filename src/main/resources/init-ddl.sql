-- -----------------------------------------------------
-- DDL
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table category
-- -----------------------------------------------------
CREATE TABLE CATEGORY
(
    CATEGORY_ID  INT NOT NULL PRIMARY KEY,
    NAME         VARCHAR(50) NOT NULL
);

-- -----------------------------------------------------
-- Table book
-- -----------------------------------------------------
CREATE TABLE BOOK
(
    BOOK_ID           INT NOT NULL PRIMARY KEY,
    TITLE             VARCHAR(100) NOT NULL,
    PUBLICATIONYEAR   INT,
    NUMBERPAGES       INT,
    CATEGORY          INT NOT NULL,
    QUANTITY          INT,
    CONSTRAINT FK_Category FOREIGN KEY (CATEGORY) REFERENCES CATEGORY (CATEGORY_ID) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table author
-- -----------------------------------------------------
CREATE TABLE AUTHOR
(
    AUTHOR_ID  INT NOT NULL PRIMARY KEY,
    FIRSTNAME  VARCHAR(20),
    LASTNAME   VARCHAR(20)
);

-- -----------------------------------------------------
-- Table book_author
-- -----------------------------------------------------
CREATE TABLE BOOK_AUTHOR
(
    BOOK_ID    INT NOT NULL,
    AUTHOR_ID  INT NOT NULL,
    PRIMARY KEY (BOOK_ID, AUTHOR_ID),
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK ON DELETE CASCADE,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE USER
(
    USER_ID      INT IDENTITY NOT NULL PRIMARY KEY,
    LOGIN        VARCHAR(20) NOT NULL,
    PASSWORD     VARCHAR(100) NOT NULL,
    FIRSTNAME    VARCHAR(30) NOT NULL,
    LASTNAME     VARCHAR(30) NOT NULL,
    PHONENUMBER  BIGINT,
    EMAIL        VARCHAR(20) NOT NULL,
    ROLE         VARCHAR(10) NOT NULL
);

-- -----------------------------------------------------
-- Table order_book
-- -----------------------------------------------------
CREATE TABLE ORDER_BOOK
(
    ORDER_ID  INT IDENTITY NOT NULL PRIMARY KEY,
    BOOK_ID   INT NOT NULL,
    USER_ID   INT NOT NULL,
    RESERVED  VARCHAR(20) NOT NULL,
    APPROVED  BOOLEAN NOT NULL,
    FOREIGN KEY (BOOK_ID) REFERENCES BOOK,
    FOREIGN KEY (USER_ID) REFERENCES USER
);

COMMIT;
