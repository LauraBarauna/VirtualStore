package br.com.lauraBarauna.controller.admin;

import br.com.lauraBarauna.dto.admin.AdminRequestDTO;
import br.com.lauraBarauna.dto.admin.AdminResponseDTO;
import br.com.lauraBarauna.service.ServiceFactory;
import br.com.lauraBarauna.service.admin.AdminService;

public class AdminController {
    private  AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    public void createAdmin(String email, String additional_role) {
        AdminRequestDTO dto = new AdminRequestDTO(email, additional_role);

        try {
            this.service.createAdmin(dto);
            System.out.println("Admin created successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void showAllAdmins() {
        try {
            for (AdminResponseDTO admin : this.service.getAllAdmins()) {
                System.out.println(admin);
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateOneAdmin(String email, String additional_role) {
        AdminRequestDTO dto = new AdminRequestDTO(email, additional_role);
        try {
            this.service.updateAdmin(dto);
            System.out.println("Admin updated successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteOneAdmin(String email) {
        try {
            this.service.deleteAdminById(email);
            System.out.println("Admin deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteAllAdmins() {
        try {
            this.service.deleteAllAdmins();
            System.out.println("All admins deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AdminController controller = ServiceFactory.createAdminController();
        controller.createAdmin("lauraNovo@email.com", "boss");
    }
}
