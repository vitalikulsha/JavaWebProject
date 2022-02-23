package io.github.vitalikulsha.JavaWebProject.entity;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private int id;
    private String title;
    private List<Author> authors;
    private int yearIssue;
    private int numberPages;
    private Category category;

    public Book(int id, String title, List<Author> authors, int yearIssue, int numberPages, Category category) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.yearIssue = yearIssue;
        this.numberPages = numberPages;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public int getYearIssue() {
        return yearIssue;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", category=" + category +
                '}';
    }
}
