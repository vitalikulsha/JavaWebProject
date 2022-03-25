package io.github.vitalikulsha.JavaWebProject.service.impl;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverter;
import io.github.vitalikulsha.JavaWebProject.entity.converter.DtoConverterFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;
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
    public BookDto getById(int id) throws ServiceException {
        try {
            return bookDtoConverter.toDto(bookDao.findById(id));
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting book from DB by id", e);
        }
    }

    @Override
    public List<BookDto> getAll() throws ServiceException {
        try {
            return bookDao.findAll()
                    .stream()
                    .map(bookDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting all books from DB", e);
        }
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) throws ServiceException {
        try {
            return bookDao.findByBookTitle(title)
                    .stream()
                    .map(bookDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting books from DB by title", e);
        }
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) throws ServiceException {
        try {
            return bookDao.findByAuthorName(authorName)
                    .stream()
                    .map(bookDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting books from DB by author name", e);
        }
    }

    @Override
    public List<BookDto> getBooksByCategoryName(String categoryName) throws ServiceException {
        try {
            return bookDao.findByCategoryName(categoryName)
                    .stream()
                    .map(bookDtoConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting books from DB by category name", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws ServiceException {
        return false;
    }

    @Override
    public boolean removeOneBook(int bookId) throws ServiceException {
        try {
            Book book = bookDao.findById(bookId);
            int quantityBooks = book.getNumber();
            if (quantityBooks > 0) {
                return bookDao.updateQuantityBooks(quantityBooks - 1, bookId) == 1;
            } else {
                log.error(book.getTitle() + " out of stock, awaiting delivery.");
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception when removing one book", e);
        }
    }

    @Override
    public boolean addOneBook(int bookId) throws ServiceException {
        try {
            Book book = bookDao.findById(bookId);
            return bookDao.updateQuantityBooks(book.getNumber() + 1, bookId) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when adding one book", e);
        }
    }
}
