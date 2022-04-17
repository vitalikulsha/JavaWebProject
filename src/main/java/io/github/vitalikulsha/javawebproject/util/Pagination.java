package io.github.vitalikulsha.javawebproject.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class for paginating a list.
 */
public class Pagination {
    /**
     * Number of entries per page.
     */
    private int itemsOnPage;
    /**
     * Start index for pagination.
     */
    private int fromIndex;

    public Pagination(int page, int itemsOnPage) {
        this.fromIndex = (page - 1) * itemsOnPage;
        this.itemsOnPage = itemsOnPage;
    }

    public int getItemsOnPage() {
        return itemsOnPage;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    /**
     * Gets a list of pages.
     *
     * @param numberPages quantity of pages
     * @return list of pages
     */
    public List<Integer> getPages(int numberPages) {
        int numPage = (numberPages / itemsOnPage) + (numberPages % itemsOnPage == 0 ? 0 : 1);
        return IntStream.range(1, numPage + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public void setItemsOnPage(int itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    public void setFromIndex(int page) {
        this.fromIndex = (page - 1) * itemsOnPage;
    }
}
