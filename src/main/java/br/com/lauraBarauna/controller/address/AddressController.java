package br.com.lauraBarauna.controller.address;

import br.com.lauraBarauna.dto.address.AddresRequestDTO;
import br.com.lauraBarauna.dto.address.AddressResponseDTO;
import br.com.lauraBarauna.service.address.AddressService;

public class AddressController {
    AddressService service = new AddressService();

    public void createAddress(int userId, String state, String city, String street, String number,
                              String complement, String neighborhood, String zipCode) {

        AddresRequestDTO dto = new AddresRequestDTO(userId, state, city, street, number, complement, neighborhood, zipCode);

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

    public void updateAddress(int userId, String state, String city, String street, String number,
                              String complement, String neighborhood, String zipCode, int addressId) {
        AddresRequestDTO dto = new AddresRequestDTO(userId, state, city, street, number, complement, neighborhood, zipCode);

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
