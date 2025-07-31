package br.com.lauraBarauna.dto.store;

public class StoreResponseDTO {
    private int id;
    private String name;
    private String cnpj;
    private String description;

    public StoreResponseDTO(int id, String name, String cnpj, String description) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.description = description;
    }

    @Override
    public String toString() {
        return "StoreResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
