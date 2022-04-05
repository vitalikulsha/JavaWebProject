package io.github.vitalikulsha.javawebproject.book.service;

import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DtoConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DtoConverterFactory;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final DtoConverter<BookDTO, Book> bookDtoConverter;

    public BookServiceImpl() {
        bookDao = DaoFactory.instance().bookDao();
        bookDtoConverter = DtoConverterFactory.instance().bookDtoConverter();
    }

    @Override
    public BookDTO getById(int id) throws ServiceException {
        try {
            return bookDtoConverter.toDto(bookDao.findById(id));
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting book from DB by id", e);
        }
    }

    @Override
    public List<BookDTO> getAll() throws ServiceException {
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
    public List<BookDTO> getBooksByTitle(String title) throws ServiceException {
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
    public List<BookDTO> getBooksByAuthorName(String authorName) throws ServiceException {
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
    public List<BookDTO> getBooksByCategoryName(String categoryName) throws ServiceException {
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
        try {
            return bookDao.deleteById(id) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when deleting a book", e);
        }
    }

    @Override
    public boolean removeOneBook(int bookId) throws ServiceException {
        try {
            Book book = bookDao.findById(bookId);
            int quantityBooks = book.getQuantity();
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
            return bookDao.updateQuantityBooks(book.getQuantity() + 1, bookId) == 1;
        } catch (DaoException e) {
            throw new ServiceException("Exception when adding one book", e);
        }
    }
}
