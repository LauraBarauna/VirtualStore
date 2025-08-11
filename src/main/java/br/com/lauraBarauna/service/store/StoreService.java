package br.com.lauraBarauna.service.store;

import br.com.lauraBarauna.dto.store.StoreRequestDTO;
import br.com.lauraBarauna.dto.store.StoreResponseDTO;
import br.com.lauraBarauna.model.store.Store;
import br.com.lauraBarauna.model.user.User;
import br.com.lauraBarauna.repository.store.StoreRepository;
import br.com.lauraBarauna.service.user.UserService;

import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private final StoreRepository REPOSITORY = new StoreRepository();
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public StoreResponseDTO modelToDTO(Store store) {
        return new StoreResponseDTO(store.getId(), store.getName(), store.getCnpj(), store.getDescription());
    }

    public Store DTOToModel (StoreRequestDTO storeDTO) {
        return Store.fromCreation(storeDTO.getUser_id(), storeDTO.getName(), storeDTO.getCnpj(), storeDTO.getDescription());
    }

    public void doesStoreExist(int id) {
        Store store = this.REPOSITORY.findStoreById(id);
        if (store == null) {
            throw new RuntimeException("Store with id " + id + " not found.");
        }
    }

    public boolean doesUserHaveStore(String email) {
        User user = this.userService.getUserByEmail(email);

        if (!this.REPOSITORY.userHaveStore(user.getId())) {
            return false;
        }

        return true;
    }

    public void createStore(StoreRequestDTO storeDTO) {
        this.REPOSITORY.addStore(DTOToModel(storeDTO));
    }

    public StoreResponseDTO getStoreById(int id) {
        doesStoreExist(id);
        return modelToDTO(this.REPOSITORY.findStoreById(id));
    }

    public List<StoreResponseDTO> getAllStores() {
        List<Store> stores = this.REPOSITORY.findAllStores();
        List<StoreResponseDTO> dtos = new ArrayList<>();

        if (stores.isEmpty()) {
            throw new RuntimeException("No stores found.");
        }

        for (Store store : stores) {
            dtos.add(modelToDTO(store));
        }

        return dtos;
    }

    public void updateStore(StoreRequestDTO storeDTO) {
        doesStoreExist(storeDTO.getId());
        this.REPOSITORY.updateStore(DTOToModel(storeDTO));
    }

}
