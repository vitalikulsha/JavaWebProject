package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.RecordBook;

import java.util.List;

public interface RecordBookDao extends Dao<RecordBook, Integer>{
    List<RecordBook> getByName(String title);

    List<RecordBook> getByAuthorName(String name);

    List<RecordBook> getByCategoryName(String name);
}
