package br.com.lauraBarauna.repository.admin;

import br.com.lauraBarauna.db.MySQLConnection;
import br.com.lauraBarauna.model.admin.Admin;
import br.com.lauraBarauna.model.common.Email;
import br.com.lauraBarauna.model.common.Phone;
import br.com.lauraBarauna.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO admins (user_id, additional_role) VALUES (?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,admin.getUser().getId());
            stmt.setString(2,admin.getAdditionalRole());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAdmin(Admin admin) {
        String sql = "UPDATE admins SET additional_role = ? WHERE user_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,admin.getAdditionalRole());
            stmt.setInt(2,admin.getUser().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin findAdminById(Admin admin) {
        String sql =
                "SELECT a.user_id AS admin_id, u.name, u.email, u.phone, a.additional_role" +
                "FROM admins a" +
                "JOIN users u ON a.user_id = u.id" +
                "WHERE a.user_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, admin.getUser().getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    Email emailObj = Email.fromDatabase(email);
                    Phone phoneObj = Phone.fromDataBase(phone);
                    String role = rs.getString("additional_role");
                    return Admin.fromDataBase(User.fromDataBase(admin.getUser().getId(), name, emailObj, null, phoneObj), role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Admin> findAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql =
                "SELECT a.user_id AS admin_id, u.name, u.email, u.phone, a.additional_role" +
                        "FROM admins a" +
                        "JOIN users u ON a.user_id = u.id";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("admin_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Email emailObj = Email.fromDatabase(email);
                Phone phoneObj = Phone.fromDataBase(phone);
                String role = rs.getString("additional_role");
                User user = User.fromDataBase(id, name, emailObj, null, phoneObj);
                admins.add(Admin.fromDataBase(user, role));
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }

    public void deleteOneAdmin(int userId) {
        String sql = "DELETE FROM admins WHERE user_id = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,userId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllAdmins() {
        String sql = "DELETE FROM admins";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
