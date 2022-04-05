package io.github.vitalikulsha.javawebproject.book.entity;

import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.category.entity.Category;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Book DTO bean class from book class
 */
public class BookDTO implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private String title;
    private List<Author> authors;
    private int publicationYear;
    private int numberPages;
    private Category category;
    private int quantity;

    public BookDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDto = (BookDTO) o;
        return id == bookDto.id && publicationYear == bookDto.publicationYear && numberPages == bookDto.numberPages && quantity == bookDto.quantity && Objects.equals(title, bookDto.title) && Objects.equals(authors, bookDto.authors) && Objects.equals(category, bookDto.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authors, publicationYear, numberPages, category, quantity);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publicationYear=" + publicationYear +
                ", numberPages=" + numberPages +
                ", category=" + category +
                ", quantity=" + quantity +
                '}';
    }

    /**
     * Book DTO builder from book class
     */
    public static class Builder {
        private BookDTO bookDto;

        public Builder() {
            bookDto = new BookDTO();
        }

        public Builder fixId(int id) {
            bookDto.id = id;
            return this;
        }

        public Builder fixTitle(String title) {
            bookDto.title = title;
            return this;
        }

        public Builder fixAuthors(List<Author> authors) {
            bookDto.authors = authors;
            return this;
        }

        public Builder fixPublicationYear(int publicationYear) {
            bookDto.publicationYear = publicationYear;
            return this;
        }

        public Builder fixNumberPages(int numberPages) {
            bookDto.numberPages = numberPages;
            return this;
        }

        public Builder fixCategory(Category category) {
            bookDto.category = category;
            return this;
        }

        public Builder fixQuantity(int quantity) {
            bookDto.quantity = quantity;
            return this;
        }

        public BookDTO build() {
            return bookDto;
        }

    }
}
