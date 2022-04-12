package io.github.vitalikulsha.javawebproject.book.service;

import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final DTOConverter<BookDTO, Book> bookDTOConverter;

    public BookServiceImpl() {
        bookDao = DaoFactory.instance().bookDao();
        bookDTOConverter = DTOConverterFactory.instance().bookDtoConverter();
    }

    @Override
    public BookDTO getById(int id) throws ServiceException {
        try {
            return bookDTOConverter.toDto(bookDao.findById(id));
        } catch (DaoException e) {
            log.error("Unable to get book by id.");
            throw new ServiceException("Exception when getting book from DB by id", e);
        }
    }

    @Override
    public List<BookDTO> getAll(Pagination pagination) throws ServiceException {
        try {
            return bookDao.findAll(pagination.getFromIndex(), pagination.getItemsOnPage())
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Unable to get all books.");
            throw new ServiceException("Exception when getting all books from DB", e);
        }
    }

    @Override
    public List<BookDTO> getBooksByTitle(Pagination pagination, String title) throws ServiceException {
        try {
            return bookDao.findByBookTitle(pagination.getFromIndex(), pagination.getItemsOnPage(), title)
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Unable to get books by title.");
            throw new ServiceException("Exception when getting books from DB by title", e);
        }
    }


    @Override
    public List<BookDTO> getBooksByAuthorName(Pagination pagination, String authorName) throws ServiceException {
        try {
            return bookDao.findByAuthorName(pagination.getFromIndex(), pagination.getItemsOnPage(), authorName)
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Unable to get books by author name.");
            throw new ServiceException("Exception when getting books from DB by author name", e);
        }
    }

    @Override
    public List<BookDTO> getBooksByCategoryName(Pagination pagination, String categoryName) throws ServiceException {
        try {
            return bookDao.findByCategoryName(pagination.getFromIndex(), pagination.getItemsOnPage(), categoryName)
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            log.error("Unable to get books by category name.");
            throw new ServiceException("Exception when getting books from DB by category name", e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            bookDao.deleteById(id);
        } catch (DaoException e) {
            log.error("Unable to delete book by id.");
            throw new ServiceException("Exception when deleting a book", e);
        }
    }

    @Override
    public int countBySearchParam(Column column, String searchParam) throws ServiceException {
        try {
            return bookDao.countBySearchParam(column, searchParam);
        } catch (DaoException e) {
            throw new ServiceException("Exception when counting records", e);
        }
    }

    @Override
    public int countAll() throws ServiceException {
        try {
            return bookDao.countAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception when counting records", e);
        }
    }

    @Override
    public void decrementQuantityBook(int bookId) throws ServiceException {
        try {
            Book book = bookDao.findById(bookId);
            int quantityBooks = book.getQuantity();
            if (quantityBooks == 0) {
                log.error(book.getTitle() + " out of stock, awaiting delivery.");
                throw new ServiceException("The quantity of books is zero.");
            }
            book.setQuantity(--quantityBooks);
            bookDao.update(book);
        } catch (DaoException e) {
            throw new ServiceException("Exception when removing one book", e);
        }
    }

    @Override
    public void incrementQuantityBook(int bookId) throws ServiceException {
        try {
            Book book = bookDao.findById(bookId);
            int quantityBooks = book.getQuantity();
            book.setQuantity(++quantityBooks);
            bookDao.update(book);
        } catch (DaoException e) {
            throw new ServiceException("Exception when adding one book", e);
        }
    }
}
