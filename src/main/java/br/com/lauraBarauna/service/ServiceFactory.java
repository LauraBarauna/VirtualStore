package br.com.lauraBarauna.service;

import br.com.lauraBarauna.controller.admin.AdminController;
import br.com.lauraBarauna.controller.user.UserController;
import br.com.lauraBarauna.service.admin.AdminService;
import br.com.lauraBarauna.service.user.UserService;

public class ServiceFactory {

    public static AdminController createAdminController() {
        AdminService adminService = new AdminService();
        UserService userService = new UserService();

        adminService.setUserService(userService);
        userService.setAdminService(adminService);

        return new AdminController(adminService);
    }

    public static UserController createUserController() {
        UserService userService = new UserService();
        AdminService adminService = new AdminService();

        adminService.setUserService(userService);
        userService.setAdminService(adminService);

        return new UserController(userService);
    }

}
