package io.github.vitalikulsha.javawebproject.book.service;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.Paging;
import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverterFactory;
import io.github.vitalikulsha.javawebproject.util.service.ServiceFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceImplTest {
    BookService bookService;
    DTOConverter<BookDTO, Book> dtoConverter;
    List<BookDTO> bookDTOList;
    Paging<BookDTO> paging;
    Pagination pagination;

    @Before
    public void init() {
        bookService = ServiceFactory.instance().bookService();
        dtoConverter = DTOConverterFactory.instance().bookDtoConverter();
        bookDTOList = DataBase.BOOK_TABLE.stream()
                .map(b -> dtoConverter.toDto(b))
                .collect(Collectors.toList());
        paging = new Paging<>(1, ConfigParameter.ITEMS_ON_PAGE);
        pagination = new Pagination(1, ConfigParameter.ITEMS_ON_PAGE);
    }

    @Test
    public void getById() throws ServiceException {
        BookDTO expected = bookDTOList.stream()
                .filter(b -> b.getId() == 12300)
                .findFirst()
                .get();
        assertEquals(expected, bookService.getById(12300));
        assertNotEquals(expected, bookService.getById(11200));
        assertNull(bookService.getById(1));
    }

    @Test
    public void getAll() throws ServiceException {
        assertEquals(paging.paginate(bookDTOList), bookService.getAll(pagination));
        pagination.setFromIndex(3);
        paging.setFirstIndex(3);
        assertEquals(paging.paginate(bookDTOList), bookService.getAll(pagination));
        pagination.setFromIndex(10);
        assertTrue(bookService.getAll(pagination).isEmpty());
    }

    @Test
    public void getBooksByTitle() throws ServiceException {
        List<BookDTO> expected = bookDTOList.stream()
                .filter(b -> b.getTitle().contains("и"))
                .collect(Collectors.toList());
        assertEquals(paging.paginate(expected), bookService.getBooksByTitle(pagination, "и"));
        assertNotEquals(paging.paginate(expected), bookService.getBooksByTitle(pagination, "И"));
        assertTrue(bookService.getBooksByTitle(pagination, "title").isEmpty());
    }

    @Test
    public void getBooksByAuthorName() throws ServiceException {
        List<BookDTO> expected = new ArrayList<Book>() {{
            this.add(new Book(20001, "Краткая история времени", 2019, 272, 20, 1));
            this.add(new Book(20002, "Природа пространства и время", 2022, 192, 20, 10));
        }}.stream()
                .map(b -> dtoConverter.toDto(b))
                .collect(Collectors.toList());
        assertEquals(paging.paginate(expected), bookService.getBooksByAuthorName(pagination, "Хок"));
        assertNotEquals(paging.paginate(expected), bookService.getBooksByAuthorName(pagination, "хок"));
        assertTrue(bookService.getBooksByAuthorName(pagination, "name").isEmpty());
    }

    @Test
    public void getBooksByCategoryName() throws ServiceException {
        List<BookDTO> expected = bookDTOList.stream()
                .filter(b -> b.getCategory().getName().contains("науки"))
                .collect(Collectors.toList());
        assertEquals(paging.paginate(expected), bookService.getBooksByCategoryName(pagination, "науки"));
        assertNotEquals(paging.paginate(expected), bookService.getBooksByCategoryName(pagination, "НАУКИ"));
        assertTrue(bookService.getBooksByCategoryName(pagination, "категория").isEmpty());
    }

    @Test
    public void updateMethods() throws ServiceException {
        bookService.deleteById(12300);
        assertNull(bookService.getById(12300));
        bookService.decrementQuantityBook(10100);
        int quantity = bookDTOList.stream()
                .filter(b -> b.getId() == 10100)
                .findFirst()
                .map(BookDTO::getQuantity)
                .get();
        assertEquals(--quantity, bookService.getById(10100).getQuantity());
        bookService.incrementQuantityBook(10100);
        assertEquals(++quantity, bookService.getById(10100).getQuantity());
    }

    @Test
    public void countAllAndBySearchParam() throws ServiceException {
        assertEquals(bookDTOList.size(), bookService.countAll());
        long expected = bookDTOList.stream()
                .filter(b -> b.getCategory().getName().contains("науки"))
                .count();
        assertEquals(expected, bookService.countBySearchParam(Column.NAME, "науки"));
        assertEquals(0, bookService.countBySearchParam(Column.NAME, "НАУКИ"));
    }
}