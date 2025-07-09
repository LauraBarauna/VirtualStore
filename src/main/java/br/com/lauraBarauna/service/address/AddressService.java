package br.com.lauraBarauna.service.address;

import br.com.lauraBarauna.dto.address.AddresRequestDTO;
import br.com.lauraBarauna.dto.address.AddressResponseDTO;
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

    public void doesUserHaveAddress(int userId) {
        List<Address> address = this.repository.findAllUserAddress(userId);
        if (address.isEmpty()) {
            throw new RuntimeException("This user does not have any address.");
        }
    }

    public void createAddress(AddresRequestDTO dto) {
        this.repository.addAddress(Address.fromCreation(0, dto.getUserId(), dto.getState(), dto.getCity(), dto.getStreet(),
                dto.getNumber(), dto.getComplement(), dto.getNeighborhood(), dto.getZipCode()));
    }

    public List<AddressResponseDTO> getAllUserAddress(int userId) {
        doesUserHaveAddress(userId);
        this.userService.doesUserExist(userId);

        List<Address> address = this.repository.findAllUserAddress(userId);
        List<AddressResponseDTO> dtos = new ArrayList<>();
        for (Address address1 : address) {
            dtos.add(this.toDTO(address1));
        }

        return dtos;
    }

    public AddressResponseDTO getOneUserAddress(int userId, int addressId) {
        this.userService.doesUserExist(userId);
        doesUserHaveAddress(userId);
        Address address = this.repository.findOneUserAddress(userId, addressId);
        return this.toDTO(address);
    }

    public void updateAddress (AddresRequestDTO dto, int addressId) {

        this.userService.doesUserExist(dto.getUserId());

        List<Address> address = this.repository.findAllUserAddress(dto.getUserId());
        if (address.isEmpty()) {
            throw new RuntimeException("This user does not have any address.");
        }

        this.repository.updateAddress(Address.fromUpdate(0, dto.getUserId(), dto.getState(), dto.getCity(), dto.getStreet(), dto.getNumber(),
                dto.getComplement(), dto.getNeighborhood(), dto.getZipCode()), addressId);
    }

    public void deleteOneAddress(int addressId, int userId) {

        this.userService.doesUserExist(userId);

        List<Address> address = this.repository.findAllUserAddress(userId);
        if (address.isEmpty()) {
            throw new RuntimeException("This user does not have any address.");
        }
        this.repository.deleteOneAddress(addressId, userId);
    }

    public void deleteAll(int userId) {

        this.userService.doesUserExist(userId);

        List<Address> address = this.repository.findAllUserAddress(userId);
        if (address.isEmpty()) {
            throw new RuntimeException("This user does not have any address.");
        }
        this.repository.deleteAllAddress(userId);
    }

}
