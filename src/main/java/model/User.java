package model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private Email email;
    private Password password;
    private Phone phone;
    private LocalDateTime createdAt;

    public User(String name, Email email, Phone phone, int id) {
        this.name = name;
        this.email = email;
        this.password = null;
        this.phone = phone;
        this.id = id;
    }

    public User(String name, Email email, Password password, Phone phone, int id) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.id = id;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email.getEmailAddress();
    }

    public String getPassword() {
        return password.getHashedPassword();
    }

    public String getPhone() {
        return phone.getNumber();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email=" + email +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", createdAt=" + createdAt +
                '}';
    }
}
