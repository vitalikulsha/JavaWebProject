package io.github.vitalikulsha.JavaWebProject.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Pagination<T> {
    private final int itemPerPage;

    public Pagination(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public List<Integer> getPageNumbers(List<T> allItems) {
        List<Integer> pages = new ArrayList<>();
        IntStream.range(1, getNumberPages(allItems) + 1).forEach(pages::add);
        return pages;
    }

    public List<T> getItemsPerPage(List<T> allItems, int page) {
        int size = allItems.size();
        int firstIndex = (page - 1) * itemPerPage;
        int lastIndex = firstIndex + itemPerPage - 1;
        List<T> result = new ArrayList<>();
        if (size < firstIndex) {
            return result;
        }
        if (size < (lastIndex + 1)) {
            lastIndex = size - 1;
        }
        for (int i = firstIndex; i <= lastIndex; i++) {
            result.add(allItems.get(i));
        }
        return result;
    }

    private int getNumberPages(List<T> allItems) {
        int size = allItems.size();
        if (size == 0) {
            return 1;
        } else {
            return (size / itemPerPage) + (size % itemPerPage == 0 ? 0 : 1);
        }
    }

}
