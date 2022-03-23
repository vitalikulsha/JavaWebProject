package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.service.BookService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final DtoConverter<BookDto, Book> bookDtoConverter;

    public BookServiceImpl() {
        bookDao = DaoFactory.instance().bookDao();
        bookDtoConverter = DtoConverterFactory.instance().bookDtoConverter();
    }

    @Override
    public BookDto getById(int id) {
        return bookDtoConverter.toDto(bookDao.findById(id));
    }

    @Override
    public List<BookDto> getAll() {
        return bookDao.findAll()
                .stream()
                .map(bookDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) {
        return bookDao.findByBookTitle(title)
                .stream()
                .map(bookDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {
        return bookDao.findByAuthorName(authorName)
                .stream()
                .map(bookDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getBooksByCategoryName(String categoryName) {
        return bookDao.findByCategoryName(categoryName)
                .stream()
                .map(bookDtoConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean removeOneBook(int bookId) {
        Book book = bookDao.findById(bookId);
        int numberBooks = book.getNumber();
        if (numberBooks > 0) {
            return bookDao.updateNumberBooks(numberBooks - 1, bookId) != 0;
        } else {
            log.error(book.getTitle() + " out of stock, awaiting delivery.");
            return false;
        }
    }

    @Override
    public boolean addOneBook(int bookId) {
        Book book = bookDao.findById(bookId);
        return bookDao.updateNumberBooks(book.getNumber() + 1, bookId) != 0;
    }
}
