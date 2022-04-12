package io.github.vitalikulsha.javawebproject.util;

import io.github.vitalikulsha.javawebproject.config.ConfigParameter;

public class Pagination {
    private final int fromIndex;
    private final int itemsOnPage = ConfigParameter.ITEMS_ON_PAGE;

    public Pagination(int page) {
        this.fromIndex = (page - 1) * itemsOnPage;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public int getItemsOnPage() {
        return itemsOnPage;
    }
}
