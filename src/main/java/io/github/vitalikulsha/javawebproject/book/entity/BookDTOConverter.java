package io.github.vitalikulsha.javawebproject.book.entity;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.category.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.category.entity.Category;
import io.github.vitalikulsha.javawebproject.util.dtoconverter.DTOConverter;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BookDTOConverter implements DTOConverter<BookDTO, Book> {

    @Override
    public BookDTO toDto(Book book) {
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
            log.error("DaoException: category or/and authors is null.");
            return null;
        }
        return new BookDTO.Builder()
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
