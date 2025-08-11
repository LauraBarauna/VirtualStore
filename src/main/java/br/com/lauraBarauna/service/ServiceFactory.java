package br.com.lauraBarauna.service;

import br.com.lauraBarauna.controller.admin.AdminController;
import br.com.lauraBarauna.controller.store.StoreController;
import br.com.lauraBarauna.controller.user.UserController;
import br.com.lauraBarauna.service.admin.AdminService;
import br.com.lauraBarauna.service.store.StoreService;
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
        StoreService storeService = new StoreService();

        adminService.setUserService(userService);
        userService.setAdminService(adminService);
        storeService.setUserService(userService);

        return new UserController(userService);
    }

    public static StoreController createStoreController() {
        StoreService storeService = new StoreService();
        UserService userService = new UserService();

        userService.setStoreService(storeService);
        storeService.setUserService(userService);

        return new StoreController(storeService);
    }

}
