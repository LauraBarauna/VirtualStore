package br.com.lauraBarauna.service.address;

import br.com.lauraBarauna.dto.address.AddresRequestDTO;
import br.com.lauraBarauna.dto.address.AddressResponseDTO;
import br.com.lauraBarauna.dto.user.UserResponseDTO;
import br.com.lauraBarauna.model.address.Address;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.repository.address.AddressRepository;
import br.com.lauraBarauna.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    AddressRepository repository = new AddressRepository();
    UserService userService = new UserService();

    public AddressResponseDTO toDTO(Address address) {
        return new AddressResponseDTO(address.getId(), address.getUserId(), address.getState(), address.getCity(), address.getStreet(), address.getNumber(),
                address.getComplement(), address.getNeighborhood(), address.getZipCode());
    }

    public void createAddress(AddresRequestDTO dto) {
        this.repository.addAddress(Address.fromCreation(0, dto.getUserId(), dto.getState(), dto.getCity(), dto.getStreet(),
                dto.getNumber(), dto.getComplement(), dto.getNeighborhood(), dto.getZipCode()));
    }

    public List<AddressResponseDTO> getAllUserAddress(int userId) {
        List<Address> address = this.repository.findAllUserAddress(userId);
        UserResponseDTO user;

        user = userService.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found.");
        }

        if (address.isEmpty()) {
            throw new RuntimeException("This user does not have any address.");
        }

        List<AddressResponseDTO> dtos = new ArrayList<>();
        for (Address address1 : address) {
            dtos.add(this.toDTO(address1));
        }

        return dtos;
    }

    public void updateAddress (AddresRequestDTO dto, int addressId) {
        UserResponseDTO user = userService.getUserById(dto.getUserId());
        if (user == null) {
            throw new RuntimeException("User with id " + dto.getUserId() + " not found.");
        }

        this.repository.updateAddress(Address.fromUpdate(0, dto.getUserId(), dto.getState(), dto.getCity(), dto.getStreet(), dto.getNumber(),
                dto.getComplement(), dto.getNeighborhood(), dto.getZipCode()), addressId);
    }

}
