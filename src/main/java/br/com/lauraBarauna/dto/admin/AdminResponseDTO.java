package br.com.lauraBarauna.dto.admin;

public class AdminResponseDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String additionalRole;

    public AdminResponseDTO(int id, String name, String email, String phone, String additionalRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.additionalRole = additionalRole;
    }

    @Override
    public String toString() {
        return "AdminResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", additionalRole='" + additionalRole + '\'' +
                '}';
    }
}
