package io.github.vitalikulsha.JavaWebProject.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private int bookId;
    private int userId;
    private ReserveStatus reserveStatus;
    private boolean approved;

    public Order(int id, int bookId, int userId, ReserveStatus reserveStatus, boolean approved) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.reserveStatus = reserveStatus;
        this.approved = approved;
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

    public ReserveStatus getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(ReserveStatus reserveStatus) {
        this.reserveStatus = reserveStatus;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", reserveStatus=" + reserveStatus +
                ", isApproved=" + approved +
                '}';
    }

}
