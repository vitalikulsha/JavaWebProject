package io.github.vitalikulsha.JavaWebProject.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private Book book;
    private User user;
    private LocationReserve locationReserve;
    private boolean isApproved;

    public Order(int id, Book book, User user, LocationReserve locationReserve, boolean isApproved) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.locationReserve = locationReserve;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocationReserve getLocationReserve() {
        return locationReserve;
    }

    public boolean isApproved() {
        return isApproved;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", locationReserve='" + locationReserve.get() + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }

    public enum LocationReserve {
        READING_ROOM("Читальный зал"),
        LOANED("Абонемент"),
        AVAILABLE("Доступна");
        private final String locationReserve;

        LocationReserve(String locationReserve) {
            this.locationReserve = locationReserve;
        }

        public String get() {
            return locationReserve;
        }
    }
}
