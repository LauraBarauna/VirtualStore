package br.com.lauraBarauna.dto.admin;

public class AdminRequestDTO {
    private String name;
    private String password;
    private String email;
    private String phone;

    public AdminRequestDTO(String name, String password, String email, String phone) {
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
