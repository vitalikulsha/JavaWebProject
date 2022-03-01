package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.dto.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.dto.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<BookDto> getAllPage(int items){
        return bookDao.findAll(items)
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
    public BookDto save(BookDto bookDto) {
        return null;
    }

    @Override
    public void delete(BookDto bookDto) {

    }
}
