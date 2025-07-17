package br.com.lauraBarauna.dto.admin;

public class AdminRequestDTO {

    private String email;
    private String additionalRole;

    public AdminRequestDTO(String email, String additionalRole) {
        this.email = email;
        this.additionalRole = additionalRole;
    }

    public String getEmail() {
        return email;
    }

    public String getAdditionalRole() {
        return additionalRole;
    }
}
