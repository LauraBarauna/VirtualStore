package br.com.lauraBarauna.controller.admin;

import br.com.lauraBarauna.dto.admin.AdminRequestDTO;
import br.com.lauraBarauna.dto.admin.AdminResponseDTO;
import br.com.lauraBarauna.model.admin.Admin;
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

    public void showAllAdmins() {
        try {
            for (AdminResponseDTO admin : this.SERVICE.getAllAdmins()) {
                System.out.println(admin);
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateOneAdmin(String email, String additional_role) {
        AdminRequestDTO dto = new AdminRequestDTO(email, additional_role);
        try {
            this.SERVICE.updateAdmin(dto);
            System.out.println("Admin updated successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteOneAdmin(String email) {
        try {
            this.SERVICE.deleteAdminById(email);
            System.out.println("Admin deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteAllAdmins() {
        try {
            this.SERVICE.deleteAllAdmins();
            System.out.println("All admins deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AdminController controller = new AdminController();
        controller.createAdmin("lauraNovo@email.com", "boss");
    }
}
