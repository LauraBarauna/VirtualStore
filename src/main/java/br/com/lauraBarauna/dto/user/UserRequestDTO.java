package br.com.lauraBarauna.dto.user;

public class UserRequestDTO {

    private String name;
    private String password;
    private String email;
    private String phone;

    public UserRequestDTO(String name, String password, String email, String phone) {

        if (name == null || password == null || email == null || phone == null || name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            throw new IllegalArgumentException("Password, name, phone and email are required, can't be null or empty.");
        }

        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
