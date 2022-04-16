package io.github.vitalikulsha.javawebproject.util;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;

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
    private final int itemsOnPage = ConfigParameter.ITEMS_ON_PAGE;
    /**
     * Start index for pagination.
     */
    private final int fromIndex;

    public Pagination(int page) {
        this.fromIndex = (page - 1) * itemsOnPage;
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
     * @param count quantity of pages
     * @return list of pages
     */
    public List<Integer> getPages(int count) {
        int numPage = (count / itemsOnPage) + (count % itemsOnPage == 0 ? 0 : 1);
        return IntStream.range(1, numPage + 1)
                .boxed()
                .collect(Collectors.toList());
    }
}
