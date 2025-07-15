package br.com.lauraBarauna.controller.address;

import br.com.lauraBarauna.dto.address.AddresRequestDTO;
import br.com.lauraBarauna.dto.address.AddressResponseDTO;
import br.com.lauraBarauna.service.address.AddressService;

public class AddressController {
    AddressService service = new AddressService();

    public void createAddress(int userId, String state, String city, String street, String number,
                              String complement, String neighborhood, String zipCode, String label) {

        AddresRequestDTO dto = new AddresRequestDTO(userId, state, city, street, number, complement, neighborhood, zipCode, label);

        try {
            this.service.createAddress(dto);
            System.out.println("Address created successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void showAllUsersAddress(int userId) {
        try {
            for (AddressResponseDTO address : this.service.getAllUserAddress(userId)) {
                System.out.println(address);
            }
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showOneAddress(int userId, int addressId) {
        try {
            System.out.println(this.service.getOneUserAddress(addressId, userId));
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showOneAddressByLabel(String label, int userId) {
        try {
            System.out.println(this.service.getOneUserAddressByLabel(label, userId));
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateAddress(int userId, String state, String city, String street, String number,
                              String complement, String neighborhood, String zipCode, String label, int addressId) {
        AddresRequestDTO dto = new AddresRequestDTO(userId, state, city, street, number, complement, neighborhood, zipCode, label);

        try {
            this.service.updateAddress(dto, addressId);
            System.out.println("Address updated successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteOneAddress(int addressId, int userId) {
        try {
            this.service.deleteOneAddress(addressId, userId);
            System.out.println("Address deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteAll(int userId) {
        try {
            this.service.deleteAll( userId);
            System.out.println("All Address deleted successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
