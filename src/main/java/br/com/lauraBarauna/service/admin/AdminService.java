package br.com.lauraBarauna.service.admin;

import br.com.lauraBarauna.dto.admin.AdminRequestDTO;
import br.com.lauraBarauna.model.admin.Admin;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.repository.admin.AdminRepository;
import br.com.lauraBarauna.service.user.UserService;


public class AdminService {
    private final AdminRepository REPOSITORY = new AdminRepository();
    private final UserService USER_SERVICE = new UserService();

    public void createAdmin(AdminRequestDTO adminDTO) {
        this.REPOSITORY.addAdmin(Admin.fromCreation(this.USER_SERVICE.getUserByEmail(adminDTO.getEmail()), adminDTO.getAdditionalRole()));
    }

    public void updateAdmin(AdminRequestDTO adminDTO) {
        User user = this.USER_SERVICE.getUserByEmail(adminDTO.getEmail());
        this.REPOSITORY.updateAdmin(Admin.fromUpdate(user, adminDTO.getAdditionalRole()));
    }
}
