package io.github.vitalikulsha.JavaWebProject.util;

import io.github.vitalikulsha.JavaWebProject.util.constant.Attribute;
import io.github.vitalikulsha.JavaWebProject.util.constant.Parameter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Pagination class
 *
 * @param <T> element/entity type for this pagination
 */
public class Pagination<T> {
    /**
     * Number of entries per page.
     */
    private final int itemPerPage;

    public Pagination(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    /**
     * Paginates the given list of entities and puts the resulting list into the request with the given attribute.
     *
     * @param allItems  list of entities
     * @param request   HTTP servlet request
     * @param attribute request attribute
     */
    public void paginate(List<T> allItems, HttpServletRequest request, String attribute) {
        String page = request.getParameter(Parameter.PAGE);
        int pageNumber = (page == null) ? 1 : Integer.parseInt(page);
        List<Integer> pages = getPageNumbers(allItems);
        List<T> itemsPerPage = getItemsPerPage(allItems, pageNumber);
        request.setAttribute(attribute, itemsPerPage);
        request.setAttribute(Attribute.PAGES, pages);
    }

    private List<Integer> getPageNumbers(List<T> allItems) {
        List<Integer> pages = new ArrayList<>();
        IntStream.range(1, getQuantityPages(allItems) + 1).forEach(pages::add);
        return pages;
    }

    private List<T> getItemsPerPage(List<T> allItems, int pageNumber) {
        int size = allItems.size();
        int firstIndex = (pageNumber - 1) * itemPerPage;
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

    private int getQuantityPages(List<T> allItems) {
        int size = allItems.size();
        if (size == 0) {
            return 1;
        } else {
            return (size / itemPerPage) + (size % itemPerPage == 0 ? 0 : 1);
        }
    }
}
