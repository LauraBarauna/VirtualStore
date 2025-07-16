package br.com.lauraBarauna.dto.admin;

public class AdminResponseDTO {
    private int id;
    private String name;
    private String email;
    private String phone;

    public AdminResponseDTO(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AdminResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
