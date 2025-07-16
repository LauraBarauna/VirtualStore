package br.com.lauraBarauna.model.admin;

import br.com.lauraBarauna.model.common.Email;
import br.com.lauraBarauna.model.common.Password;
import br.com.lauraBarauna.model.common.Phone;
import br.com.lauraBarauna.model.user.User;

import java.time.LocalDateTime;

public class Admin {
    private int id;
    private String name;
    private Email email;
    private Password password;
    private Phone phone;
    private LocalDateTime createdAt;

    private Admin(int id, String name, Email email, Password password, Phone phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public Admin fromCreation(String name, Email email, Password password, Phone phone) {
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
        return new Admin(0, name, email, password, phone);
    }

    public Admin fromUpdate(int id, String name, Email email, Password password, Phone phone) {
        return new Admin(id, name, email, password, phone);
    }

    public Admin fromDataBase (int id, String name, Email email, Password password, Phone phone) {
        return new Admin(id, name, email, password, phone);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Phone getPhone() {
        return phone;
    }
}
