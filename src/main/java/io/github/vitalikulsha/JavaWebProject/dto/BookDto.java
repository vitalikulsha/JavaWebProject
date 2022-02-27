package io.github.vitalikulsha.JavaWebProject.dto;

import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Category;

import java.io.Serializable;
import java.util.List;

public class BookDto implements Serializable {
    private int id;
    private String title;
    private List<Author> authors;
    private int publicationYear;
    private int numberPages;
    private Category category;
    private int number;

    public BookDto() {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
                ", number=" + number +
                '}';
    }

    public static class Builder {
        private BookDto bookDto;

        public Builder() {
            bookDto = new BookDto();
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

        public Builder fixNumber(int number) {
            bookDto.number = number;
            return this;
        }

        public BookDto build() {
            return bookDto;
        }

    }
}
