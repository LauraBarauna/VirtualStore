package model;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private Email email;
    private String password;
    private Phone phone;
    private LocalDateTime createdAt;

    public User(String password, String name, String email, String phone) {

        if (password == null || password.isEmpty() || name == null || name.isEmpty() || phone == null || phone.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Password, name, phone and email are required, can't be null or empty.");
        } else if (password.length() < 8 || password.length() > 16) {
            throw new IllegalArgumentException("Password must be between 8 and 16 characters.");
        }

        this.password = password;
        this.name = name;
        this.email = new Email(email);
        this.phone = new Phone(phone);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
