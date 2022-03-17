package io.github.vitalikulsha.JavaWebProject.dto;

import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.entity.Category;

import java.io.Serializable;
import java.util.List;

public class bookDto implements Serializable {
    private int id;
    private String title;
    private List<Author> authors;
    private int publicationYear;
    private int numberPages;
    private Category category;
    private int number;

}
