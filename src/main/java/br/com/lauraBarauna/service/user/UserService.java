package br.com.lauraBarauna.service.user;

import br.com.lauraBarauna.dto.user.UserRequestDTO;
import br.com.lauraBarauna.dto.user.UserResponseDTO;
import br.com.lauraBarauna.model.common.Email;
import br.com.lauraBarauna.model.common.Password;
import br.com.lauraBarauna.model.common.Phone;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository repository = new UserRepository();

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
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPhone());
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
        User user = this.repository.findUserByEmail(email);

        if (user == null) {
            throw new RuntimeException("Email " + email + " not found.");
        }
        if (!user.login(password)) {
            throw new RuntimeException("Invalid password.");
        }

        return entityToDto(user);
    }

}
