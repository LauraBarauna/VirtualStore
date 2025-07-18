package br.com.lauraBarauna.model.address;

import java.time.LocalDateTime;

public class Address {
    private int id;
    private int userId;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipCode;
    private String label;
    private LocalDateTime createdAt;

    private Address(int id, int userId, String state, String city, String street, String number,
                   String complement, String neighborhood, String zipCode, String label) {

        this.id = id;
        this.userId = userId;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.label = label;
    }

    public static Address fromCreation(int id, int userId, String state, String city, String street, String number,
                                       String complement, String neighborhood, String zipCode, String label) {

        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }

        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or empty.");
        }

        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty.");
        }

        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty.");
        }

        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be null or empty.");
        }

        if (complement == null || complement.isBlank()) {
            throw new IllegalArgumentException("Complement cannot be null or empty.");
        }

        if (neighborhood == null || neighborhood.isBlank()) {
            throw new IllegalArgumentException("Neighborhood cannot be null or empty.");
        }

        if (zipCode == null || !zipCode.matches("\\d{8}")) {
            throw new IllegalArgumentException("Zip code must be 8 numeric digits.");
        }

        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Label cannot be null or empty.");
        }

        return new Address(id, userId, state, city, street, number, complement, neighborhood, zipCode, label);
    }

    public static Address fromUpdate(int id, int userId, String state, String city, String street, String number,
                                     String complement, String neighborhood, String zipCode, String label) {
        return new Address(id, userId, state, city, street, number, complement, neighborhood, zipCode, label);
    }

    public static Address fromDataBase(int id, int userId, String state, String city, String street, String number,
                                     String complement, String neighborhood, String zipCode, String label) {
        return new Address(id, userId, state, city, street, number, complement, neighborhood, zipCode, label);
    }

    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive.");
        }
        this.userId = userId;
    }

    public void setState(String state) {
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State cannot be null or empty.");
        }
        this.state = state.trim();
    }

    public void setCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty.");
        }
        this.city = city.trim();
    }

    public void setStreet(String street) {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be null or empty.");
        }
        this.street = street.trim();
    }

    public void setNumber(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Number cannot be null or empty.");
        }
        this.number = number.trim();
    }

    public void setComplement(String complement) {
        this.complement = complement == null ? "" : complement.trim();
    }

    public void setNeighborhood(String neighborhood) {
        if (neighborhood == null || neighborhood.isBlank()) {
            throw new IllegalArgumentException("Neighborhood cannot be null or empty.");
        }
        this.neighborhood = neighborhood.trim();
    }

    public void setZipCode(String zipCode) {
        if (zipCode == null || !zipCode.matches("\\d{8}")) {
            throw new IllegalArgumentException("Zip code must be 8 numeric digits.");
        }
        this.zipCode = zipCode;
    }

    // Getters


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getLabel() {
        return label;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
