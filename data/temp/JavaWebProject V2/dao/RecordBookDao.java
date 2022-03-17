package io.github.vitalikulsha.JavaWebProject.dao;

import java.util.List;

public interface RecordBookDao extends Dao<RecordBook>{
    List<RecordBook> getByBookTitle(String title);

    List<RecordBook> getByAuthorName(String name);

    List<RecordBook> getByCategoryName(String name);
}
