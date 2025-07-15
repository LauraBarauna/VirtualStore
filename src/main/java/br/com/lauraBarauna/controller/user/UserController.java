package br.com.lauraBarauna.controller.user;

import br.com.lauraBarauna.dto.user.UserRequestDTO;
import br.com.lauraBarauna.dto.user.UserResponseDTO;
import br.com.lauraBarauna.service.user.UserService;

import java.util.List;

public class UserController {

    UserService service = new UserService();

    public void createNewUser(String name, String password, String email, String phone) {
        try {
            UserRequestDTO dto = new UserRequestDTO(name, password, email, phone);
            this.service.createUser(dto);
            System.out.println("User created successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showAllUsers() {
        try {
            for (UserResponseDTO user : this.service.getAllUsers()) {
                System.out.println(user);
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showUserById(int id) {
        try {
            System.out.println(this.service.getUserById(id));
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateUser(int id, String name, String email, String phone) {
        try {
            UserRequestDTO dto = new UserRequestDTO(name, null, email, phone);
            this.service.changeUser(dto, id);
            System.out.println("User updated successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updatePassword(int id, String newPassword) {
        try {
            this.service.changeUserPassword(id, newPassword);
            System.out.println("User password updated successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            this.service.deleteUser(id);
            System.out.println("User deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public UserResponseDTO loginUser(String email, String password) {
        try {
            UserResponseDTO dto = this.service.loginUser(email, password);
            System.out.println("User logged successfully!");
            return dto;
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }


}
