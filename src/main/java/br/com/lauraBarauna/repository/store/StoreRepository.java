package br.com.lauraBarauna.repository.store;

import br.com.lauraBarauna.db.MySQLConnection;
import br.com.lauraBarauna.model.store.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRepository {

    public void modelo() {
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement()) {


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStore(Store store) {
        String sql = "INSERT INTO stores (user_id, name, cnpj, description) VALUES (?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, store.getUser_id());
            stmt.setString(2, store.getName());
            stmt.setString(3, store.getCnpj());
            stmt.setString(4, store.getDescription());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Store findStoreById(int id) {
        String sql = "SELECT id, name, cnpj, description FROM stores WHERE id = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String cnpj = rs.getString("cnpj");
                    String description = rs.getString("description");
                    Store store = Store.fromDataBase(id, name, cnpj, description);
                    return store;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
