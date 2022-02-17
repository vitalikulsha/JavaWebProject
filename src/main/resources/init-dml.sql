-- DML

INSERT INTO CATEGORY VALUES (10, 'Энциклопедии');
INSERT INTO CATEGORY VALUES (20, 'Естественные науки');
INSERT INTO CATEGORY VALUES (30, 'Техника. Технические науки');
INSERT INTO CATEGORY VALUES (40, 'Сельское и лесное хозяйство');
INSERT INTO CATEGORY VALUES (50, 'Здравоохранение. Медицинские науки');
INSERT INTO CATEGORY VALUES (60, 'Социальные и гуманитарные науки');
INSERT INTO CATEGORY VALUES (70, 'Культура. Наука. Просвещение');
INSERT INTO CATEGORY VALUES (80, 'Филологические науки. Художественная литература');
INSERT INTO CATEGORY VALUES (90, 'Литература универсального содержания');

INSERT INTO BOOK VALUES (10100, 'Энциклопедия относительного и абсолютного знания', 2014, 288, 10);
INSERT INTO BOOK VALUES (11200, 'Толкиен и его мир', 2005, 496, 10);
INSERT INTO BOOK VALUES (12300, 'Гномы', 2005, 496, 10);
INSERT INTO BOOK VALUES (13400, 'Что непонятно у классиков, или Энциклопедия русского быта XIX века', 2005, 496, 10);

INSERT INTO BOOK VALUES (20001, 'Краткая история времени', 2019, 272, 20);
INSERT INTO BOOK VALUES (20002, 'Природа пространства и время', 2022, 192, 20);
INSERT INTO BOOK VALUES (21001, 'Большой роман о математике', 2018, 320, 20);
INSERT INTO BOOK VALUES (22001, '99 секретов химии', 2020, 224, 20);

INSERT INTO BOOK VALUES (30001, 'Технологическая оснастка', 2012, 288, 30);
INSERT INTO BOOK VALUES (31001, 'Проектирование и строительство. Дом, квартира, сад', 2016, 256, 30);

INSERT INTO BOOK VALUES (40001, 'Организация крестьянского хозяйства', 2015, 363, 40);
INSERT INTO BOOK VALUES (41001, 'Организация охотничьего хозяйства: учебное пособие', 2016, 268, 40);

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

