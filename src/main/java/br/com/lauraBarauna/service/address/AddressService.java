package br.com.lauraBarauna.service.address;

import br.com.lauraBarauna.dto.address.AddresRequestDTO;
import br.com.lauraBarauna.dto.address.AddressResponseDTO;
import br.com.lauraBarauna.model.address.Address;
import br.com.lauraBarauna.repository.address.AddressRepository;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    AddressRepository repository = new AddressRepository();

    public AddressResponseDTO toDTO(Address address) {
        return new AddressResponseDTO(address.getId(), address.getUserId(), address.getState(), address.getCity(), address.getStreet(), address.getNumber(),
                address.getComplement(), address.getNeighborhood(), address.getZipCode());
    }

    public void createAddress(AddresRequestDTO dto) {
        this.repository.addAddress(new Address(0, dto.getUserId(), dto.getState(), dto.getCity(), dto.getStreet(),
                dto.getNumber(), dto.getComplement(), dto.getNeighborhood(), dto.getZipCode()));
    }

    public List<AddressResponseDTO> getAllUserAddress(int userId) {
        List<Address> address = this.repository.findAllUserAddress(userId);

        if (address.isEmpty()) {
            throw new RuntimeException("User with id " + userId + " not found.");
        }

        List<AddressResponseDTO> dtos = new ArrayList<>();
        for (Address address1 : address) {
            dtos.add(this.toDTO(address1));
        }

        return dtos;
    }

}
