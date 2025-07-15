package br.com.lauraBarauna.repository.user;

import br.com.lauraBarauna.db.MySQLConnection;
import br.com.lauraBarauna.model.common.Email;
import br.com.lauraBarauna.model.common.Password;
import br.com.lauraBarauna.model.common.Phone;
import br.com.lauraBarauna.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // READ
    public List<User> findAllUsers() {
        String sql = "SELECT id, name, email, phone FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Email emailObj = Email.fromDatabase(email);
                Phone phoneObj = Phone.fromDataBase(phone);
                users.add(User.fromDataBase(id, name, emailObj, null, phoneObj));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findUserById(int id) {
        String sql = "SELECT id, name, email, phone FROM users WHERE id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    Email emailObj = Email.fromDatabase(email);
                    Phone phoneObj = Phone.fromDataBase(phone);
                    return User.fromDataBase(id, name, emailObj, null, phoneObj);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // UPDATE

    public void updateUser(User user, int id) {
        StringBuilder sql = new StringBuilder("UPDATE users SET ");
        List<Object> params = new ArrayList<>();

        if (user.getName() != null && !user.getName().isEmpty()) {
            sql.append("name = ?, ");
            params.add(user.getName());
        }

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            sql.append("email = ?, ");
            params.add(user.getEmail());
        }

        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            sql.append("phone = ?, ");
            params.add(user.getPhone());
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(id);

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void updateUserPassword(User user) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getPassword());
            stmt.setInt(2, user.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUserByEmail(String email) {
        String sql = "SELECT id, name, email, phone, password FROM users WHERE email = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Email emailObj = Email.fromDatabase(email);
                    Phone phoneObj = Phone.fromDataBase(rs.getString("phone"));
                    Password savedPassword =  Password.fromHashed(rs.getString("password"));
                    String name = rs.getString("name");
                    int id = rs.getInt("id");
                    return User.fromDataBase(id, name, emailObj, savedPassword, phoneObj);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
