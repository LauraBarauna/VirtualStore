package br.com.lauraBarauna.repository.admin;

import br.com.lauraBarauna.db.MySQLConnection;
import br.com.lauraBarauna.model.admin.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
