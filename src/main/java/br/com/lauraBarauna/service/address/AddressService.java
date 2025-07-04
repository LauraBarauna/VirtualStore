package br.com.lauraBarauna.service.address;

import br.com.lauraBarauna.dto.address.AddresRequestDTO;
import br.com.lauraBarauna.model.address.Address;
import br.com.lauraBarauna.repository.address.AddressRepository;

public class AddressService {
    AddressRepository repository = new AddressRepository();

    public void createAddress(AddresRequestDTO dto) {

        this.repository.addAddress(new Address(0, dto.getUserId(), dto.getState(), dto.getCity(), dto.getStreet(),
                dto.getNumber(), dto.getComplement(), dto.getNeighborhood(), dto.getZipCode()));
    }

}
