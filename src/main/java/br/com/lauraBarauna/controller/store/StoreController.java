package br.com.lauraBarauna.controller.store;

import br.com.lauraBarauna.service.store.StoreService;

public class StoreController {
    private StoreService service;

    public StoreController(StoreService service) {
        this.service = service;
    }
}
