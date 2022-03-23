package io.github.vitalikulsha.JavaWebProject.entity;

import java.io.Serializable;

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

    public User(int id, String login, String password, String firstName,  String lastName, long phoneNumber, String email, Role role) {
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
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
