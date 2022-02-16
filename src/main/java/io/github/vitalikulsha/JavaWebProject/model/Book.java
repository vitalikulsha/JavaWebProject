package io.github.vitalikulsha.JavaWebProject.model;

import java.util.Objects;

public class Book {
    private final Long id;
    private final String title;
    private final String author;
    private final String publisher;
    private final Integer yearIssue;
    private final Category category;

    public Book(Long id, String title, String author, String publisher, Integer yearIssue, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearIssue = yearIssue;
        this.category = category;
    }
}
