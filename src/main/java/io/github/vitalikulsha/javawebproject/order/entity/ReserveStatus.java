package io.github.vitalikulsha.javawebproject.order.entity;

/**
 * Order reserve status enum
 */
public enum ReserveStatus {
    READING_ROOM("Читальный зал"),
    LOANED("Абонемент"),
    REFUND("Возврат");

    private final String title;

    ReserveStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}