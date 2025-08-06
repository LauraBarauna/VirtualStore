package br.com.lauraBarauna.model.store;

public class Store {
    private int id;
    private int user_id;
    private String name;
    private String cnpj;
    private String description;

    private Store(int id, int user_id, String name, String cnpj, String description) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.cnpj = cnpj;
        this.description = description;
    }

    public static Store fromCreation (int user_id, String name, String cnpj, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (cnpj == null || cnpj.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        return new Store(0, user_id, name, cnpj, description);
    }

    public static Store fromDataBase (int id, String name, String cnpj, String description) {
        return new Store(id, 0, name, cnpj, description);
    }

    public static Store fromUpdate (int user_id, String name, String cnpj, String description) {
        return new Store(1, user_id, name, cnpj, description);
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
