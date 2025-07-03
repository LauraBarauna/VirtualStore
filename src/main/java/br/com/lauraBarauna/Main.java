package br.com.lauraBarauna;

import br.com.lauraBarauna.controller.user.UserController;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.service.user.UserService;


public class Main {
    public static void main(String[] args) {

        UserController userController = new UserController();

        userController.loginUser("luiza@email.com", "laura12345");
    }
}
