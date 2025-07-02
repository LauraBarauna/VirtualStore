package br.com.lauraBarauna.dto.user;

public class UserResponseDTO {

    private int id;
    private String name;
    private String email;
    private String phone;

    public UserResponseDTO(int id, String name, String email, String phone) {

        if (id < 0 || name == null || email == null || phone == null || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            throw new IllegalArgumentException("Password, name, phone, created at and email are required, can't be null or empty.");
        }

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
