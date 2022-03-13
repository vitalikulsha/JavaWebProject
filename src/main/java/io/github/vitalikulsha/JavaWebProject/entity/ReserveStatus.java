package io.github.vitalikulsha.JavaWebProject.entity;

public enum ReserveStatus {
    READING_ROOM("Читальный зал"),
    LOANED("Абонемент");

    private final String title;

    ReserveStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
