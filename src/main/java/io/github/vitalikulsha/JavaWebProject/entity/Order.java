package io.github.vitalikulsha.JavaWebProject.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int bookId;
    private int userId;
    private LocationReserve locationReserve;
    private boolean isApproved;

    public Order(int id, int bookId, int userId, LocationReserve locationReserve, boolean isApproved) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.locationReserve = locationReserve;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocationReserve getLocationReserve() {
        return locationReserve;
    }

    public void setLocationReserve(LocationReserve locationReserve) {
        this.locationReserve = locationReserve;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", locationReserve=" + locationReserve +
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
