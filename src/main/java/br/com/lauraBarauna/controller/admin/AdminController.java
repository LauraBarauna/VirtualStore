package br.com.lauraBarauna.controller.admin;

import br.com.lauraBarauna.dto.admin.AdminRequestDTO;
import br.com.lauraBarauna.service.admin.AdminService;

public class AdminController {
    private final AdminService SERVICE = new AdminService();

    public void createAdmin(String email, String additional_role) {
        AdminRequestDTO dto = new AdminRequestDTO(email, additional_role);

        try {
            this.SERVICE.createAdmin(dto);
            System.out.println("Admin created successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        AdminController controller = new AdminController();
        controller.createAdmin("lauraNovo@email.com", "boss");
    }
}
