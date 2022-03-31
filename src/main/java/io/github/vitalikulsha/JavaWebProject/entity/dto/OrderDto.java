package io.github.vitalikulsha.JavaWebProject.entity.dto;

import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;

import java.io.Serializable;
import java.util.Objects;

/**
 * Order DTO bean class
 */
public class OrderDto implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private BookDto bookDto;
    private UserDto userDto;
    private ReserveStatus reserveStatus;
    private boolean approved;

    private OrderDto() {
    }

    public OrderDto(int id, BookDto bookDto, UserDto userDto, ReserveStatus reserveStatus, boolean approved) {
        this.id = id;
        this.bookDto = bookDto;
        this.userDto = userDto;
        this.reserveStatus = reserveStatus;
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return id == orderDto.id && approved == orderDto.approved && Objects.equals(bookDto, orderDto.bookDto) && Objects.equals(userDto, orderDto.userDto) && reserveStatus == orderDto.reserveStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookDto, userDto, reserveStatus, approved);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", book=" + bookDto +
                ", user=" + userDto +
                ", reserveStatus=" + reserveStatus +
                ", isApproved=" + approved +
                '}';
    }

    /**
     * Order DTO builder
     */
    public static class Builder {
        private OrderDto orderDto;

        public Builder() {
            orderDto = new OrderDto();
        }

        public Builder fixId(int id) {
            orderDto.id = id;
            return this;
        }

        public Builder fixBookDto(BookDto bookDto) {
            orderDto.bookDto = bookDto;
            return this;
        }

        public Builder fixUser(UserDto userDto) {
            orderDto.userDto = userDto;
            return this;
        }

        public Builder fixReserveStatus(ReserveStatus reserveStatus) {
            orderDto.reserveStatus = reserveStatus;
            return this;
        }

        public Builder fixApproved(boolean approved) {
            orderDto.approved = approved;
            return this;
        }

        public OrderDto build() {
            return orderDto;
        }
    }
}
