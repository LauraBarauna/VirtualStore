package dto;

import java.time.LocalDateTime;

public class UserResponseDTO {

    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

    public UserResponseDTO(int id, String name, String password, String email, String phone, LocalDateTime createdAt) {

        if (createdAt == null || id < 0 || name == null || password == null || email == null || phone == null || name.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            throw new IllegalArgumentException("Password, name, phone, created at and email are required, can't be null or empty.");
        }

        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
