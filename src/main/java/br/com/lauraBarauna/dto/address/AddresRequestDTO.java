package br.com.lauraBarauna.dto.address;

public class AddresRequestDTO {

    private int userId;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipCode;

    public AddresRequestDTO(int userId, String state, String city, String street, String number,
                            String complement, String neighborhood, String zipCode) {

        this.userId = userId;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
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
}
