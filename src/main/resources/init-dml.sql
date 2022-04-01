-- -----------------------------------------------------
-- DML
-- -----------------------------------------------------

-- category
INSERT INTO CATEGORY VALUES (10, 'Энциклопедии');
INSERT INTO CATEGORY VALUES (20, 'Естественные науки');
INSERT INTO CATEGORY VALUES (30, 'Техника. Технические науки');
INSERT INTO CATEGORY VALUES (40, 'Сельское и лесное хозяйство');
INSERT INTO CATEGORY VALUES (50, 'Здравоохранение. Медицинские науки');
INSERT INTO CATEGORY VALUES (60, 'Социальные и гуманитарные науки');
INSERT INTO CATEGORY VALUES (70, 'Культура. Наука. Просвещение');
INSERT INTO CATEGORY VALUES (80, 'Филологические науки. Художественная литература');
INSERT INTO CATEGORY VALUES (90, 'Литература универсального содержания');

-- book
INSERT INTO BOOK VALUES (10100, 'Энциклопедия относительного и абсолютного знания', 2014, 288, 10, 5);
INSERT INTO BOOK VALUES (11200, 'Толкиен и его мир', 2005, 496, 10, 6);
INSERT INTO BOOK VALUES (12300, 'Гномы', 2005, 496, 10, 3);
INSERT INTO BOOK VALUES (13400, 'Что непонятно у классиков, или Энциклопедия русского быта XIX века', 2005, 496, 10, 2);
INSERT INTO BOOK VALUES (20001, 'Краткая история времени', 2019, 272, 20, 1);
INSERT INTO BOOK VALUES (20002, 'Природа пространства и время', 2022, 192, 20, 10);
INSERT INTO BOOK VALUES (21001, 'Большой роман о математике', 2018, 320, 20, 4);
INSERT INTO BOOK VALUES (22001, '99 секретов химии', 2020, 224, 20, 6);
INSERT INTO BOOK VALUES (30001, 'Технологическая оснастка', 2012, 288, 30, 8);
INSERT INTO BOOK VALUES (31001, 'Проектирование и строительство. Дом, квартира, сад', 2016, 256, 30, 5);
INSERT INTO BOOK VALUES (40001, 'Организация крестьянского хозяйства', 2015, 363, 40, 6);
INSERT INTO BOOK VALUES (41001, 'Организация охотничьего хозяйства: учебное пособие', 2016, 268, 40, 4);
INSERT INTO BOOK VALUES (50001, 'Гомеопатия и не только', 2018, 368, 50, 3);
INSERT INTO BOOK VALUES (50002, 'Практическая психосоматика', 2019, 324, 50, 2);
INSERT INTO BOOK VALUES (60001, 'Загадки истории', 2011, 320, 60, 1);
INSERT INTO BOOK VALUES (61001, 'Теория государства и права', 2013, 652, 60, 10);
INSERT INTO BOOK VALUES (70001, 'Закат Европы', 2006, 630, 70, 3);
INSERT INTO BOOK VALUES (71001, 'Твое здоровье в твоих руках', 1973, 56, 70, 4);
INSERT INTO BOOK VALUES (80001, 'Народные русские сказки', 2018, 280, 80, 5);
INSERT INTO BOOK VALUES (81001, '1984', 2015, 320, 80, 6);
INSERT INTO BOOK VALUES (81002, 'Триумфальная арка', 2017, 640, 80, 7);
INSERT INTO BOOK VALUES (82001, 'Преступление и наказание', 2019, 608, 80, 4);
INSERT INTO BOOK VALUES (82101, 'Знак беды', 1989, 542, 80, 3);
INSERT INTO BOOK VALUES (90001, 'Великие цитаты и афоризмы', 2021, 319, 90, 1);
INSERT INTO BOOK VALUES (90002, 'Афоризмы для умных мужчин', 2021, 192, 90, 0);

-- author
INSERT INTO AUTHOR VALUES (1, 'Бернар', 'Вербер');
INSERT INTO AUTHOR VALUES (2, 'Кирилл', 'Королёв');
INSERT INTO AUTHOR VALUES (3, 'Тим', 'Аппензеллер');
INSERT INTO AUTHOR VALUES (4, 'Юрий', 'Федосюк');
INSERT INTO AUTHOR VALUES (5, 'Стивен', 'Хокинг');
INSERT INTO AUTHOR VALUES (6, 'Роджер', 'Пенроуз');
INSERT INTO AUTHOR VALUES (7, 'Микаэль', 'Лонэ');
INSERT INTO AUTHOR VALUES (8, 'Анастасия', 'Мартюшева');
INSERT INTO AUTHOR VALUES (9, 'Борис', 'Черпаков');
INSERT INTO AUTHOR VALUES (10, 'Петер', 'Нойферт');
INSERT INTO AUTHOR VALUES (11, 'Людвиг', 'Нефф');
INSERT INTO AUTHOR VALUES (12, 'Александр', 'Чаянов');
INSERT INTO AUTHOR VALUES (13, 'Юрий', 'Мальков');
INSERT INTO AUTHOR VALUES (14, 'Евгений', 'Чешуин');
INSERT INTO AUTHOR VALUES (15, 'Эдвард', 'Радзинский');
INSERT INTO AUTHOR VALUES (16, 'Михаил', 'Марченко');
INSERT INTO AUTHOR VALUES (17, 'Татьяна', 'Колотова');
INSERT INTO AUTHOR VALUES (18, 'Юрий', 'Чикоров');
INSERT INTO AUTHOR VALUES (19, 'Освальд', 'Шпенглер');
INSERT INTO AUTHOR VALUES (20, 'Степан', 'Кривенков');
INSERT INTO AUTHOR VALUES (21, 'Александр', 'Афанасьев');
INSERT INTO AUTHOR VALUES (22, 'Джордж', 'Оруэлл');
INSERT INTO AUTHOR VALUES (23, 'Эрих Мария', 'Ремарк');
INSERT INTO AUTHOR VALUES (24, 'Федор', 'Достоевский');
INSERT INTO AUTHOR VALUES (25, 'Василь', 'Быков');
INSERT INTO AUTHOR VALUES (26, 'Омар', 'Хайам');
INSERT INTO AUTHOR VALUES (27, 'Марк', 'Фалкирк');

-- book_authors
INSERT INTO BOOK_AUTHOR VALUES (10100, 1);
INSERT INTO BOOK_AUTHOR VALUES (11200, 2);
INSERT INTO BOOK_AUTHOR VALUES (12300, 3);
INSERT INTO BOOK_AUTHOR VALUES (13400, 4);
INSERT INTO BOOK_AUTHOR VALUES (20001, 5);
INSERT INTO BOOK_AUTHOR VALUES (20002, 5);
INSERT INTO BOOK_AUTHOR VALUES (20002, 6);
INSERT INTO BOOK_AUTHOR VALUES (21001, 7);
INSERT INTO BOOK_AUTHOR VALUES (22001, 8);
INSERT INTO BOOK_AUTHOR VALUES (30001, 9);
INSERT INTO BOOK_AUTHOR VALUES (31001, 10);
INSERT INTO BOOK_AUTHOR VALUES (31001, 11);
INSERT INTO BOOK_AUTHOR VALUES (40001, 12);
INSERT INTO BOOK_AUTHOR VALUES (41001, 13);
INSERT INTO BOOK_AUTHOR VALUES (41001, 14);
INSERT INTO BOOK_AUTHOR VALUES (50001, 17);
INSERT INTO BOOK_AUTHOR VALUES (50002, 18);
INSERT INTO BOOK_AUTHOR VALUES (60001, 15);
INSERT INTO BOOK_AUTHOR VALUES (61001, 16);
INSERT INTO BOOK_AUTHOR VALUES (70001, 19);
INSERT INTO BOOK_AUTHOR VALUES (71001, 20);
INSERT INTO BOOK_AUTHOR VALUES (80001, 21);
INSERT INTO BOOK_AUTHOR VALUES (81001, 22);
INSERT INTO BOOK_AUTHOR VALUES (81002, 23);
INSERT INTO BOOK_AUTHOR VALUES (82001, 24);
INSERT INTO BOOK_AUTHOR VALUES (82101, 25);
INSERT INTO BOOK_AUTHOR VALUES (90001, 26);
INSERT INTO BOOK_AUTHOR VALUES (90002, 27);

-- user
INSERT INTO USER VALUES (1, 'Admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'Иван', 'Иванов', 375123456789, 'admin@gmail.com', 'ADMIN');
INSERT INTO USER VALUES (2, 'Librarian', '2c445e1c04df4e247c2089245b68fc811f728f7d30ff14a6d64a4faac58e6270', 'Петр', 'Петров', 375291234567, 'librarian@gmail.com', 'ADMIN');
INSERT INTO USER VALUES (3, 'User', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'Сергей', 'Сергеев', 375441234567, 'user@gmail.com', 'READER');
INSERT INTO USER VALUES (4, 'Reader', '3d0941964aa3ebdcb00ccef58b1bb399f9f898465e9886d5aec7f31090a0fb30', 'Олег', 'Олегов', 375331234567, 'reader@gmail.com', 'READER');

-- order_book
INSERT INTO ORDER_BOOK VALUES (1, 90002, 3, 'READING_ROOM', TRUE);
INSERT INTO ORDER_BOOK VALUES (2, 50001, 4, 'LOANED', FALSE);
INSERT INTO ORDER_BOOK VALUES (3, 82001, 3, 'LOANED', TRUE);
INSERT INTO ORDER_BOOK VALUES (4, 71001, 3, 'READING_ROOM', FALSE);
