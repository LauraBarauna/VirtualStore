package br.com.lauraBarauna.model.user;

import br.com.lauraBarauna.model.common.Email;
import br.com.lauraBarauna.model.common.Password;
import br.com.lauraBarauna.model.common.Phone;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private Email email;
    private Password password;
    private Phone phone;
    private LocalDateTime createdAt;

    protected User(int id, String name, Email email, Password password, Phone phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public static User fromCreation (String name, Email email, Password password, Phone phone) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (email == null) {
            throw new IllegalArgumentException("E-mail cannot be null.");
        }

        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }

        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null.");
        }

        return new User(0, name, email, password, phone);
    }

    public static User fromUpdate (String name, Email email, Password password, Phone phone) {
        return new User(0, name, email, password, phone);
    }

    public static User fromDataBase (int id, String name, Email email, Password password, Phone phone) {
        return new User(id, name, email, password, phone);
    }

    public boolean login(String rawPassword) {
        return this.password.matches(rawPassword);
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

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        this.name = name;
    }

    public void setEmail(Email email) {

        if (email == null) {
            throw new IllegalArgumentException("E-mail cannot be null.");
        }

        this.email = email;
    }

    public void setPassword(Password password) {

        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null.");
        }

        this.password = password;
    }

    public void setPhone(Phone phone) {

        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null.");
        }

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
