package io.github.vitalikulsha.JavaWebProject.domain;

import java.io.Serializable;

public class BookCatalog implements Serializable {
    private Book book;
    private Integer number;

    public BookCatalog(Book book, Integer number) {
        this.book = book;
        this.number = number;
    }

    public Book getBook() {
        return book;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "BookCatalog{" +
                "book=" + book +
                ", number=" + number +
                '}';
    }
}
