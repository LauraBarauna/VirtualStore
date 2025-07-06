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
                    addresses.add(new Address(id, userId, state, city, street, number, complement, neighborhood, zipCode));
                }
            }

            return addresses;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
