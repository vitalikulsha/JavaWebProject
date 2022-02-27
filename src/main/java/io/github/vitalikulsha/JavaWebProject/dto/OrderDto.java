package io.github.vitalikulsha.JavaWebProject.dto;

import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.entity.Order;
import io.github.vitalikulsha.JavaWebProject.entity.User;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private int id;
    private Book book;
    private User user;
    private Order.LocationReserve locationReserve;
    private boolean isApproved;

    private OrderDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order.LocationReserve getLocationReserve() {
        return locationReserve;
    }

    public void setLocationReserve(Order.LocationReserve locationReserve) {
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
        return "OrderDto{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", locationReserve=" + locationReserve +
                ", isApproved=" + isApproved +
                '}';
    }

    public static class Builder {
        private OrderDto orderDto;

        public Builder() {
            orderDto = new OrderDto();
        }

        public Builder fixId(int id) {
            orderDto.id = id;
            return this;
        }

        public Builder fixBook(Book book) {
            orderDto.book = book;
            return this;
        }

        public Builder fixUser(User user) {
            orderDto.user = user;
            return this;
        }

        public Builder fixLocationReserve(Order.LocationReserve locationReserve) {
            orderDto.locationReserve = locationReserve;
            return this;
        }

        public Builder isApproved(boolean isApproved) {
            orderDto.isApproved = isApproved;
            return this;
        }

        public OrderDto build() {
            return orderDto;
        }
    }
}
