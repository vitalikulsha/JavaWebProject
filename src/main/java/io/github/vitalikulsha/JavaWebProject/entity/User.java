package io.github.vitalikulsha.JavaWebProject.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private final static long serialVersionUID = 1L;

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private Role role;

    public User(int id, String login, String password, String firstName, String lastName,
                long phoneNumber, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        User user = (User) o;
        return id == user.id && phoneNumber == user.phoneNumber && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, firstName, lastName, phoneNumber, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone_number=" + phoneNumber +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
