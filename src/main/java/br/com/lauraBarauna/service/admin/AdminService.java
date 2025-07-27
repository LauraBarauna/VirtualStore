package br.com.lauraBarauna.service.admin;

import br.com.lauraBarauna.dto.admin.AdminRequestDTO;
import br.com.lauraBarauna.dto.admin.AdminResponseDTO;
import br.com.lauraBarauna.model.admin.Admin;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.repository.admin.AdminRepository;
import br.com.lauraBarauna.service.user.UserService;

import java.util.ArrayList;
import java.util.List;


public class AdminService {
    private final AdminRepository REPOSITORY = new AdminRepository();
    private  UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public AdminResponseDTO adminToDTO(Admin admin) {
        return new AdminResponseDTO(admin.getUser().getId(), admin.getUser().getName(), admin.getUser().getEmail(),
                admin.getUser().getPhone(), admin.getAdditionalRole());
    }

    public void superAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = this.REPOSITORY.findAdminById(Admin.fromCreation(this.userService.getUserByEmail(adminDTO.getEmail()), adminDTO.getAdditionalRole()));

        if (!admin.getAdditionalRole().equals("SUPER_ADMIN")) {
            throw new RuntimeException("You don't have permission to create a admin.");
        }
    }

    public void createAdmin(AdminRequestDTO adminDTO) {
        superAdmin(adminDTO);
        this.REPOSITORY.addAdmin(Admin.fromCreation(this.userService.getUserByEmail(adminDTO.getEmail()), adminDTO.getAdditionalRole()));
    }

    public void updateAdmin(AdminRequestDTO adminDTO) {
        superAdmin(adminDTO);
        if (!doesAdminExist(adminDTO.getEmail())) {
            throw new RuntimeException("Admin not found.");
        }
        User user = this.userService.getUserByEmail(adminDTO.getEmail());
        this.REPOSITORY.updateAdmin(Admin.fromUpdate(user, adminDTO.getAdditionalRole()));
    }

    public boolean doesAdminExist(String email) {
        User user = this.userService.getUserByEmail(email);
        return this.REPOSITORY.adminExistsByUserId(user.getId());
    }

    public AdminResponseDTO getAdminById(AdminRequestDTO adminDTO) {
        if (!doesAdminExist(adminDTO.getEmail())) {
            throw new RuntimeException("Admin not found.");
        }
        User user = this.userService.getUserByEmail(adminDTO.getEmail());
        return adminToDTO(this.REPOSITORY.findAdminById(Admin.fromDataBase(user, adminDTO.getAdditionalRole())));
    }

    public List<AdminResponseDTO> getAllAdmins() {
        List<Admin> admins = this.REPOSITORY.findAllAdmins();

        if (admins.isEmpty()) {
            throw new RuntimeException("There are no admins in the database.");
        }

        List<AdminResponseDTO> adminDTOs = new ArrayList<>();

        for (Admin admin : admins) {
            adminDTOs.add(adminToDTO(admin));
        }

        return adminDTOs;
    }

    public void deleteAdminById(String email) {
        if (!doesAdminExist(email)) {
            throw new RuntimeException("Admin not found.");
        }
        User user = this.userService.getUserByEmail(email);
        this.REPOSITORY.deleteOneAdmin(user.getId());
    }

    public void deleteAllAdmins() {
        List<Admin> admins = this.REPOSITORY.findAllAdmins();

        if (admins.isEmpty()) {
            throw new RuntimeException("There are no admins in the database to delete.");
        }

        this.REPOSITORY.deleteAllAdmins();
    }
}
