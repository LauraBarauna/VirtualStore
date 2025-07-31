package br.com.lauraBarauna.dto.store;

public class StoreRequestDTO {
    private int id;
    private int user_id;
    private String name;
    private String cnpj;
    private String description;

    public StoreRequestDTO(int id, int user_id, String name, String cnpj, String description) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.cnpj = cnpj;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getDescription() {
        return description;
    }
}
