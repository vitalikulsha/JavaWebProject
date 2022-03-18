package io.github.vitalikulsha.JavaWebProject.entity.dto;

import io.github.vitalikulsha.JavaWebProject.entity.Role;

import java.io.Serializable;

public class UserDto implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private String userName;
    private long phoneNumber;
    private String email;
    private Role role;

    public UserDto() {
    }

    public UserDto(int id, String userName, long phoneNumber, String email, Role role) {
        this.id = id;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private UserDto userDto;

        public Builder() {
            userDto = new UserDto();
        }

        public Builder fixId(int id) {
            userDto.id = id;
            return this;
        }

        public Builder fixUserName(String userName) {
            userDto.userName = userName;
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
