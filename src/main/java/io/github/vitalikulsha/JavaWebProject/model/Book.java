package io.github.vitalikulsha.JavaWebProject.model;

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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", yearIssue=" + yearIssue +
                ", numberPages=" + numberPages +
                ", category=" + category +
                '}';
    }
}
