package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.AuthorDao;
import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.CategoryDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Category;
import io.github.vitalikulsha.JavaWebProject.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private final DaoFactory factory = DaoFactory.instance();
    private final BookDao bookDao = factory.bookDao();
    private final AuthorDao authorDao = factory.authorDao();
    private final CategoryDao categoryDao = factory.categoryDao();

    @Override
    public BookDto getById(int id) {
        return toBookDto(bookDao.findById(id));
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.findAll()
                .stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) {
        return bookDao.findByBookTitle(title)
                .stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {
        return bookDao.findByAuthorName(authorName)
                .stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByCategoryName(String categoryName) {
        return bookDao.findByCategoryName(categoryName)
                .stream()
                .map(this::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto save(BookDto bookDto) {
        return null;
    }

    @Override
    public void delete(BookDto bookDto) {

    }

    private BookDto toBookDto(Book book) {
        List<Author> authors = authorDao.findAuthorsByBookId(book.getId());
        Category category = categoryDao.findById(book.getCategoryId());
        return new BookDto.Builder()
                .fixId(book.getId())
                .fixTitle(book.getTitle())
                .fixAuthors(authors)
                .fixPublicationYear(book.getPublicationYear())
                .fixNumberPages(book.getNumberPages())
                .fixCategory(category)
                .fixNumber(book.getNumber())
                .build();
    }
}
