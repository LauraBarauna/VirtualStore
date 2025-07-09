package br.com.lauraBarauna.repository.address;

import br.com.lauraBarauna.db.MySQLConnection;
import br.com.lauraBarauna.model.address.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepository {

    public void addAddress(Address address) {
        String sql = "INSERT INTO address (user_id, street, number, complement, neighborhood, city, state, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, address.getUserId());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getNumber());
            stmt.setString(4, address.getComplement());
            stmt.setString(5, address.getNeighborhood());
            stmt.setString(6, address.getCity());
            stmt.setString(7, address.getState());
            stmt.setString(8, address.getZipCode());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Address> findAllUserAddress(int userId) {
        String sql = "SELECT * FROM address WHERE user_id = ?";
        List<Address> addresses = new ArrayList<>();

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String state = rs.getString("state");
                    String city = rs.getString("city");
                    String street = rs.getString("street");
                    String number = rs.getString("number");
                    String complement = rs.getString("complement");
                    String neighborhood = rs.getString("neighborhood");
                    String zipCode = rs.getString("zip_code");
                    addresses.add(Address.fromDataBase(id, userId, state, city, street, number, complement, neighborhood, zipCode));
                }
            }

            return addresses;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAddress(Address address, int addressId) {
        StringBuilder sql = new StringBuilder("UPDATE address SET ");
        List<Object> params = new ArrayList<>();

        if (address.getState() != null && !address.getState().isBlank()) {
            sql.append("state = ?, ");
            params.add(address.getState());
        }

        if (address.getCity() != null && !address.getCity().isBlank()) {
            sql.append("city = ?, ");
            params.add(address.getCity());
        }

        if (address.getStreet() != null && !address.getStreet().isBlank()) {
            sql.append("street = ?, ");
            params.add(address.getStreet());
        }

        if (address.getNumber() != null && !address.getNumber().isBlank()) {
            sql.append("number = ?, ");
            params.add(address.getNumber());
        }

        if (address.getComplement() != null && !address.getComplement().isBlank()) {
            sql.append("complement = ?, ");
            params.add(address.getComplement());
        }

        if (address.getNeighborhood() != null && !address.getNeighborhood().isBlank()) {
            sql.append("neighborhood = ?, ");
            params.add(address.getNeighborhood());
        }

        if (address.getZipCode() != null && !address.getZipCode().isBlank()) {
            sql.append("zip_code = ?, ");
            params.add(address.getZipCode());
        }

        // Nenhum campo pra atualizar
        if (params.isEmpty()) {
            System.out.println("Nada para atualizar.");
            return;
        }

        // Remove a última vírgula e espaço
        sql.setLength(sql.length() - 2);

        // Adiciona os filtros no WHERE
        sql.append(" WHERE id = ? AND user_id = ?");
        params.add(addressId);
        params.add(address.getUserId());

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

    public void deleteOneAddress(int addressId, int userId) {
        String sql = "DELETE FROM address WHERE id = ? AND user_id = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            stmt.setInt(1, addressId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllAddress(int userId) {
        String sql = "DELETE FROM address WHERE user_id = ?";
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
