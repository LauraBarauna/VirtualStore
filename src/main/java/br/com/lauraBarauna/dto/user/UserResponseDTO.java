package br.com.lauraBarauna.dto.user;

import br.com.lauraBarauna.model.user.User;

public class UserResponseDTO {

    private int id;
    private String name;
    private String email;
    private String phone;
    private Boolean isAdmin;

    private UserResponseDTO(int id, String name, String email, String phone, Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public static UserResponseDTO fromUser(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(), null);
    }

    public static UserResponseDTO logged(User user, Boolean isAdmin) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone(), isAdmin);
    }

    public int getId() {
        return id;
    }

    public Boolean getAdmin() {
        return isAdmin;
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
