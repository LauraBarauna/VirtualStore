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
    private LocalDateTime createdAt;

    public Address(int id, int userId, String state, String city, String street, String number,
                   String complement, String neighborhood, String zipCode) {

        this.id = id;
        setUserId(userId);
        setState(state);
        setCity(city);
        setStreet(street);
        setNumber(number);
        setComplement(complement);
        setNeighborhood(neighborhood);
        setZipCode(zipCode);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
