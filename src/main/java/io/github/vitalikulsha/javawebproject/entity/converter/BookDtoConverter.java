package io.github.vitalikulsha.javawebproject.entity.converter;

import io.github.vitalikulsha.javawebproject.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.entity.Author;
import io.github.vitalikulsha.javawebproject.entity.Book;
import io.github.vitalikulsha.javawebproject.entity.Category;
import io.github.vitalikulsha.javawebproject.entity.dto.BookDto;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BookDtoConverter implements DtoConverter<BookDto, Book> {

    @Override
    public BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }
        AuthorDao authorDao = DaoFactory.instance().authorDao();
        CategoryDao categoryDao = DaoFactory.instance().categoryDao();
        List<Author> authors;
        Category category;
        try {
            category = categoryDao.findById(book.getCategoryId());
            authors = authorDao.findAuthorsByBookId(book.getId());
        } catch (DaoException e) {
            log.error("DaoException: category or/and authors is null");
            return null;
        }
        return new BookDto.Builder()
                .fixId(book.getId())
                .fixTitle(book.getTitle())
                .fixAuthors(authors)
                .fixPublicationYear(book.getPublicationYear())
                .fixNumberPages(book.getNumberPages())
                .fixCategory(category)
                .fixQuantity(book.getQuantity())
                .build();
    }
}
