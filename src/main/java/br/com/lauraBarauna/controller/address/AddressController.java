package br.com.lauraBarauna.controller.address;

import br.com.lauraBarauna.dto.address.AddresRequestDTO;
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
}
