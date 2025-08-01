package br.com.lauraBarauna.service.user;

import br.com.lauraBarauna.dto.user.UserRequestDTO;
import br.com.lauraBarauna.dto.user.UserResponseDTO;
import br.com.lauraBarauna.model.common.Email;
import br.com.lauraBarauna.model.common.Password;
import br.com.lauraBarauna.model.common.Phone;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.repository.user.UserRepository;
import br.com.lauraBarauna.service.admin.AdminService;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository repository = new UserRepository();
     private AdminService adminService;

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public User dtoToEntityCreation(UserRequestDTO dto) {
        Email email = Email.from(dto.getEmail());
        Phone phone = Phone.from(dto.getPhone());
        Password password = Password.fromPlainText(dto.getPassword());
        return User.fromCreation(dto.getName(), email, password, phone);
    }

    public User dtoToEntityUpdate(UserRequestDTO dto) {
        Email email = Email.fromDatabase(dto.getEmail());
        Phone phone = Phone.fromDataBase(dto.getPhone());
        return User.fromUpdate(dto.getName(), email, null, phone);
    }

    public UserResponseDTO entityToDto(User user) {
        return UserResponseDTO.fromUser(user);
    }

    public UserResponseDTO entityToLoggedDTO(User user, boolean isAdmin) {
        return UserResponseDTO.logged(user, isAdmin);
    }

    public void createUser(UserRequestDTO userDto) {
        this.repository.addUser(dtoToEntityCreation(userDto));
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = this.repository.findAllUsers();

        if (users.isEmpty()) {
            throw new RuntimeException("Empty products list.");
        }

        List<UserResponseDTO> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(entityToDto(user));
        }
        return dtos;
    }

    public void doesUserExist(int id) {
        User user = this.repository.findUserById(id);
        if (user == null) {
            throw new RuntimeException("User with id " + id + " not found.");
        }

    }

    public UserResponseDTO getUserById(int id) {
        doesUserExist(id);
        User user = this.repository.findUserById(id);
        return entityToDto(user);
    }

    public User getUserByEmail(String email) {
        User user = this.repository.findUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("User with email " + email + " not found.");
        }
        return user;
    }

    public void changeUser(UserRequestDTO userDto, int id) {
        doesUserExist(id);
        this.repository.updateUser(dtoToEntityUpdate(userDto), id);
    }

    public void changeUserPassword(int id, String newPassword) {
        doesUserExist(id);

        User user = this.repository.findUserById(id);
        Password password = Password.fromPlainText(newPassword);
        user.setPassword(password);
        this.repository.updateUserPassword(user);
    }

    public void deleteUser(int id) {
        doesUserExist(id);
        this.repository.deleteUserById(id);
    }

    public UserResponseDTO loginUser(String email, String password) {
        User user = getUserByEmail(email);

        if (!user.login(password)) {
            throw new RuntimeException("Invalid password.");
        }

        boolean isAdmin = this.adminService.doesAdminExist(email);

        return entityToLoggedDTO(user, isAdmin);
    }

}
