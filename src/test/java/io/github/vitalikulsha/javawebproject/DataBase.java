package io.github.vitalikulsha.javawebproject;

import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.category.entity.Category;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Database class for testing.
 */
public class DataBase {
    /**
     * Contains elements of the BOOK table.
     */
    public static final List<Book> BOOK_TABLE = new ArrayList<>() {{
        this.add(new Book(10100, "Энциклопедия относительного и абсолютного знания", 2014, 288, 10, 5));
        this.add(new Book(11200, "Толкиен и его мир", 2005, 496, 10, 6));
        this.add(new Book(12300, "Гномы", 2005, 496, 10, 3));
        this.add(new Book(13400, "Что непонятно у классиков, или Энциклопедия русского быта XIX века", 2005, 496, 10, 2));
        this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
        this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
        this.add(new Book(21001, "Большой роман о математике", 2018, 320, 20, 4));
        this.add(new Book(22001, "99 секретов химии", 2020, 224, 20, 6));
        this.add(new Book(30001, "Технологическая оснастка", 2012, 288, 30, 8));
        this.add(new Book(31001, "Проектирование и строительство. Дом, квартира, сад", 2016, 256, 30, 5));
        this.add(new Book(40001, "Организация крестьянского хозяйства", 2015, 363, 40, 6));
        this.add(new Book(41001, "Организация охотничьего хозяйства: учебное пособие", 2016, 268, 40, 4));
        this.add(new Book(50001, "Гомеопатия и не только", 2018, 368, 50, 3));
        this.add(new Book(50002, "Практическая психосоматика", 2019, 324, 50, 2));
        this.add(new Book(60001, "Загадки истории", 2011, 320, 60, 1));
        this.add(new Book(61001, "Теория государства и права", 2013, 652, 60, 10));
        this.add(new Book(70001, "Закат Европы", 2006, 630, 70, 3));
        this.add(new Book(71001, "Твое здоровье в твоих руках", 1973, 56, 70, 4));
        this.add(new Book(80001, "Народные русские сказки", 2018, 280, 80, 5));
        this.add(new Book(81001, "1984", 2015, 320, 80, 6));
        this.add(new Book(81002, "Триумфальная арка", 2017, 640, 80, 7));
        this.add(new Book(82001, "Преступление и наказание", 2019, 608, 80, 4));
        this.add(new Book(82101, "Знак беды", 1989, 542, 80, 3));
        this.add(new Book(90001, "Великие цитаты и афоризмы", 2021, 319, 90, 1));
        this.add(new Book(90002, "Афоризмы для умных мужчин", 2021, 192, 90, 0));
    }};
    /**
     * Contains elements of the AUTHOR table.
     */
    public static final List<Author> AUTHOR_TABLE = new ArrayList<>() {{
        this.add(new Author(1, "Бернар", "Вербер"));
        this.add(new Author(2, "Кирилл", "Королёв"));
        this.add(new Author(3, "Тим", "Аппензеллер"));
        this.add(new Author(4, "Юрий", "Федосюк"));
        this.add(new Author(5, "Стивен", "Хокинг"));
        this.add(new Author(6, "Роджер", "Пенроуз"));
        this.add(new Author(7, "Микаэль", "Лонэ"));
        this.add(new Author(8, "Анастасия", "Мартюшева"));
        this.add(new Author(9, "Борис", "Черпаков"));
        this.add(new Author(10, "Петер", "Нойферт"));
        this.add(new Author(11, "Людвиг", "Нефф"));
        this.add(new Author(12, "Александр", "Чаянов"));
        this.add(new Author(13, "Юрий", "Мальков"));
        this.add(new Author(14, "Евгений", "Чешуин"));
        this.add(new Author(15, "Эдвард", "Радзинский"));
        this.add(new Author(16, "Михаил", "Марченко"));
        this.add(new Author(17, "Татьяна", "Колотова"));
        this.add(new Author(18, "Юрий", "Чикоров"));
        this.add(new Author(19, "Освальд", "Шпенглер"));
        this.add(new Author(20, "Степан", "Кривенков"));
        this.add(new Author(21, "Александр", "Афанасьев"));
        this.add(new Author(22, "Джордж", "Оруэлл"));
        this.add(new Author(23, "Эрих Мария", "Ремарк"));
        this.add(new Author(24, "Федор", "Достоевский"));
        this.add(new Author(25, "Василь", "Быков"));
        this.add(new Author(26, "Омар", "Хайам"));
        this.add(new Author(27, "Марк", "Фалкирк"));
    }};
    /**
     * Contains elements of the CATEGORY table.
     */
    public static final List<Category> CATEGORY_TABLE = new ArrayList<>() {{
        this.add(new Category(10, "Энциклопедии"));
        this.add(new Category(20, "Естественные науки"));
        this.add(new Category(30, "Техника. Технические науки"));
        this.add(new Category(40, "Сельское и лесное хозяйство"));
        this.add(new Category(50, "Здравоохранение. Медицинские науки"));
        this.add(new Category(60, "Социальные и гуманитарные науки"));
        this.add(new Category(70, "Культура. Наука. Просвещение"));
        this.add(new Category(80, "Филологические науки. Художественная литература"));
        this.add(new Category(90, "Литература универсального содержания"));
    }};
    /**
     * Contains elements of the ORDER_BOOK table.
     */
    public static final List<Order> ORDER_TABLE = new ArrayList<>() {{
        this.add(new Order(1, 90002, 3, ReserveStatus.READING_ROOM, true));
        this.add(new Order(2, 50001, 4, ReserveStatus.LOANED, false));
        this.add(new Order(3, 82001, 3, ReserveStatus.LOANED, true));
        this.add(new Order(4, 71001, 3, ReserveStatus.READING_ROOM, false));
    }};
    /**
     * Contains elements of the USER table.
     */
    public static final List<User> USER_TABLE = new ArrayList<>() {{
        this.add(new User(1, "Admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                "Иван", "Иванов", 375123456789L, "admin@gmail.com", Role.ADMIN));
        this.add(new User(2, "Librarian", "2c445e1c04df4e247c2089245b68fc811f728f7d30ff14a6d64a4faac58e6270",
                "Петр", "Петров", 375291234567L, "librarian@gmail.com", Role.ADMIN));
        this.add(new User(3, "User", "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb",
                "Сергей", "Сергеев", 375441234567L, "user@gmail.com", Role.READER));
        this.add(new User(4, "Reader", "3d0941964aa3ebdcb00ccef58b1bb399f9f898465e9886d5aec7f31090a0fb30",
                "Олег", "Олегов", 375331234567L, "reader@gmail.com", Role.READER));
    }};
}
