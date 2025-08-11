package br.com.lauraBarauna.repository.store;

import br.com.lauraBarauna.db.MySQLConnection;
import br.com.lauraBarauna.model.store.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreRepository {

    public boolean userHaveStore(int id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM stores WHERE user_id = ?) AS exists_flag";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("exists_flag");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
                    return Store.fromDataBase(id, name, cnpj, description);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Store> findAllStores() {
        String sql = "SELECT id, name, cnpj, description FROM stores";
        List<Store> stores = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String cnpj = rs.getString("cnpj");
                String description = rs.getString("description");
                stores.add(Store.fromDataBase(id, name, cnpj, description));
            }

            return stores;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStore(Store store) {
        StringBuilder sql = new StringBuilder("UPDATE stores SET ");
        List<Object> params = new ArrayList<>();

        if (store.getName() != null && !store.getName().isBlank()) {
            sql.append("name = ?");
            params.add(store.getName());
        }

        if (store.getCnpj() != null && !store.getCnpj().isBlank()) {
            sql.append("cnpj = ?");
            params.add(store.getCnpj());
        }

        if (store.getDescription() != null && !store.getDescription().isBlank()) {
            sql.append("description = ?");
            params.add(store.getDescription());
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(store.getId());

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


}
