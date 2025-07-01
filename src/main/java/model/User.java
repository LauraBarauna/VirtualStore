package model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private Email email;
    private String password;
    private Phone phone;
    private LocalDateTime createdAt;

    public User(String name, Email email, String password, Phone phone) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if (password.length() < 8 || password.length() > 16) {
            throw new IllegalArgumentException("Password must be between 8 and 16 characters");
        }

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
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
        return password;
    }

    public String getPhone() {
        return phone.toString();
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

    public void setPassword(String password) {
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
