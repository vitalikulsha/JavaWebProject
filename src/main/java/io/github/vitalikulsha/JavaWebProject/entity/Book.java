package io.github.vitalikulsha.JavaWebProject.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private String title;
    private int publicationYear;
    private int numberPages;
    private int categoryId;
    private int number;

    public Book(int id, String title, int publicationYear, int numberPages, int categoryId, int number) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numberPages = numberPages;
        this.categoryId = categoryId;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", numberPages=" + numberPages +
                ", categoryId=" + categoryId +
                ", number=" + number +
                '}';
    }
}
