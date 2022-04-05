package io.github.vitalikulsha.javawebproject.order.entity;

import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.user.entity.UserDTO;

import java.io.Serializable;
import java.util.Objects;

/**
 * Order DTO bean class from order class
 */
public class OrderDTO implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private BookDTO bookDto;
    private UserDTO userDto;
    private ReserveStatus reserveStatus;
    private boolean approved;

    private OrderDTO() {
    }

    public OrderDTO(int id, BookDTO bookDto, UserDTO userDto, ReserveStatus reserveStatus, boolean approved) {
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

    public BookDTO getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDTO bookDto) {
        this.bookDto = bookDto;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
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
        OrderDTO orderDto = (OrderDTO) o;
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
     * Order DTO builder from order class
     */
    public static class Builder {
        private OrderDTO orderDto;

        public Builder() {
            orderDto = new OrderDTO();
        }

        public Builder fixId(int id) {
            orderDto.id = id;
            return this;
        }

        public Builder fixBookDto(BookDTO bookDto) {
            orderDto.bookDto = bookDto;
            return this;
        }

        public Builder fixUser(UserDTO userDto) {
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

        public OrderDTO build() {
            return orderDto;
        }
    }
}
