package io.github.vitalikulsha.JavaWebProject.entity.converter;

import io.github.vitalikulsha.JavaWebProject.dao.AuthorDao;
import io.github.vitalikulsha.JavaWebProject.dao.CategoryDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Category;
import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;

import java.util.List;

public class BookDtoConverter implements DtoConverter<BookDto, Book> {

    @Override
    public BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }
        AuthorDao authorDao = DaoFactory.instance().authorDao();
        CategoryDao categoryDao = DaoFactory.instance().categoryDao();
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
