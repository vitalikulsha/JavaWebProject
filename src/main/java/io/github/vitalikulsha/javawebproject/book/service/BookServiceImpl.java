package io.github.vitalikulsha.javawebproject.book.service;

import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
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
            throw new ServiceException("Exception when getting book from DB by id", e);
        }
    }

    @Override
    public List<BookDTO> getAll() throws ServiceException {
        return null;
    }

    @Override
    public List<BookDTO> getAllPagination(int page, int itemsOnPage) throws ServiceException {
        int firstIndex = (page - 1) * itemsOnPage;
        try {
            return bookDao.findAll(firstIndex, itemsOnPage)
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting all books from DB", e);
        }
    }

    @Override
    public List<BookDTO> getBooksByTitle(int page, int itemsOnPage, String title) throws ServiceException {
        int firstIndex = (page - 1) * itemsOnPage;
        try {
            return bookDao.findByBookTitle(firstIndex, itemsOnPage, title)
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting books from DB by title", e);
        }
    }


    @Override
    public List<BookDTO> getBooksByAuthorName(int page, int itemsOnPage, String authorName) throws ServiceException {
        int firstIndex = (page - 1) * itemsOnPage;
        try {
            return bookDao.findByAuthorName(firstIndex, itemsOnPage, authorName)
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting books from DB by author name", e);
        }
    }

    @Override
    public List<BookDTO> getBooksByCategoryName(int page, int itemsOnPage, String categoryName) throws ServiceException {
        int firstIndex = (page - 1) * itemsOnPage;
        try {
            return bookDao.findByCategoryName(firstIndex, itemsOnPage, categoryName)
                    .stream()
                    .map(bookDTOConverter::toDto)
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException("Exception when getting books from DB by category name", e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            bookDao.deleteById(id);
        } catch (DaoException e) {
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
