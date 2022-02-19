package io.github.vitalikulsha.JavaWebProject.domain;

import java.util.List;

public class Book {
    private Integer id;
    private String title;
    private List<Author> authors;
    private Integer yearIssue;
    private Integer numberPages;
    private Category category;

    public Book(Integer id, String title, List<Author> authors, Integer yearIssue, Integer numberPages, Category category) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.yearIssue = yearIssue;
        this.numberPages = numberPages;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Integer getYearIssue() {
        return yearIssue;
    }

    public Integer getNumberPages() {
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
