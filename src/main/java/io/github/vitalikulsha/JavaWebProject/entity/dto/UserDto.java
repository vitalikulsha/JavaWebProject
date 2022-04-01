package io.github.vitalikulsha.JavaWebProject.entity.dto;

import io.github.vitalikulsha.JavaWebProject.entity.Role;

import java.io.Serializable;
import java.util.Objects;

/**
 * User DTO bean class from user class
 */
public class UserDto implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private Role role;

    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, long phoneNumber, String email, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id == userDto.id && phoneNumber == userDto.phoneNumber && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(email, userDto.email) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, email, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    /**
     * User DTO builder from user class
     */
    public static class Builder {
        private UserDto userDto;

        public Builder() {
            userDto = new UserDto();
        }

        public Builder fixId(int id) {
            userDto.id = id;
            return this;
        }

        public Builder fixFirstName(String firstName) {
            userDto.firstName = firstName;
            return this;
        }

        public Builder fixLastName(String lastName) {
            userDto.lastName = lastName;
            return this;
        }

        public Builder fixPhoneNumber(long phoneNumber) {
            userDto.phoneNumber = phoneNumber;
            return this;
        }

        public Builder fixEmail(String email) {
            userDto.email = email;
            return this;
        }

        public Builder fixRole(Role role) {
            userDto.role = role;
            return this;
        }

        public UserDto build() {
            return userDto;
        }
    }
}
