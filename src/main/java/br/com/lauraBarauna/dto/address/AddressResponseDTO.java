package br.com.lauraBarauna.dto.address;

public class AddressResponseDTO {

    private int id;
    private int userId;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipCode;

    public AddressResponseDTO(int id, int userId, String state, String city, String street,
                              String number, String complement, String neighborhood, String zipCode) {

        this.id = id;
        this.userId = userId;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "AddressResponseDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
