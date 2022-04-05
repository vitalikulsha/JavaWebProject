package io.github.vitalikulsha.javawebproject.book.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Book bean class (table "book" in database)
 */
public class Book implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private String title;
    private int publicationYear;
    private int numberPages;
    private int categoryId;
    private int quantity;

    public Book() {
    }

    public Book(int id, String title, int publicationYear, int numberPages, int categoryId, int quantity) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numberPages = numberPages;
        this.categoryId = categoryId;
        this.quantity = quantity;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
        Book book = (Book) o;
        return id == book.id && publicationYear == book.publicationYear && numberPages == book.numberPages && categoryId == book.categoryId && quantity == book.quantity && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publicationYear, numberPages, categoryId, quantity);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", numberPages=" + numberPages +
                ", categoryId=" + categoryId +
                ", quantity=" + quantity +
                '}';
    }
}
