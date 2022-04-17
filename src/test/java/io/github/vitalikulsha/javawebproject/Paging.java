package io.github.vitalikulsha.javawebproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for paginating a list.
 *
 * @param <T> entity type in this Pagination
 */
public class Paging<T> {
    /**
     * Number of entries per page.
     */
    private final int itemsOnPage;
    /**
     * Start index for pagination.
     */
    private int firstIndex;

    public Paging(int pageNumber, int itemsOnPage) {
        this.firstIndex = (pageNumber - 1) * itemsOnPage;
        this.itemsOnPage = itemsOnPage;
    }

    /**
     * Gets the list of entities on the page.
     *
     * @param allItems List with all entities
     * @return list of elements on the page
     */
    public List<T> paginate(List<T> allItems) {
        int size = allItems.size();
        int lastIndex = firstIndex + itemsOnPage - 1;
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

    /**
     * Gets the starting index for pagination.
     *
     * @param pageNumber page number
     * @return start index for pagination
     */
    public int getFirstIndexFrom(int pageNumber) {
        return (pageNumber - 1) * itemsOnPage;
    }

    /**
     * Sets a new starting index for pagination.
     *
     * @param pageNumber page number
     */
    public void setFirstIndex(int pageNumber) {
        this.firstIndex = (pageNumber - 1) * itemsOnPage;
    }
}
